package com.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.lab.constant.MailConstant;
import com.lab.constant.MqConstant;
import com.lab.dto.*;
import com.lab.mapper.NotifyMapper;
import com.lab.mapper.TaskMapper;
import com.lab.mapper.UserMapper;
import com.lab.response.Page;
import com.lab.service.MailService;
import com.lab.service.NotifyService;
import com.lab.utils.PageUtil;
import com.lab.utils.UserUtil;
import com.lab.vo.NotifyListVo;
import com.lab.vo.NotifySingleVo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Resource
    private NotifyMapper notifyMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private MailService mailService;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /*
     * 监听增删改任务发送的消息，并插入notify表
     * */
    @Override
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConstant.QUEUE_NOTIFY, durable = "true"),
            exchange = @Exchange(name = MqConstant.EXCHANGE_NOTIFY, type = ExchangeTypes.TOPIC),
            key = {MqConstant.ROUTING_KEY_NOTIFY_ALL}
    ))
    public void add (NotifySendDto notifySendDto) {
        Integer notifyType = notifySendDto.getNotifyType();

        // 通知类型是新增任务
        if (notifyType == 1) {
            notifyTypeIsAdd(notifySendDto);
            // 通知类型是删除任务
        } else if (notifyType == 2) {
            notifyTypeIsDelete(notifySendDto);
            // 通知类型是更新任务
        } else if (notifyType == 3) {
            notifyTypeIsUpdate(notifySendDto);
        }

    }

    /*
     * 删除任务的情况的通知
     * */
    private void notifyTypeIsDelete (NotifySendDto notifySendDto) {
        // 通过消息中的任务主键ID，得到assignedUserIds
        List<Integer> taskIds = notifySendDto.getId();
        List<String> assignedUserIds = taskMapper.getTaskAssignedUserIds(taskIds);

        // 对assignedUserIds去重
        Set<Integer> userIdSet = new HashSet<>();
        for (String assignedUserId : assignedUserIds) {
            String[] ids = assignedUserId.split(",");
            for (String userId : ids) {
                userIdSet.add(Integer.parseInt(userId));
            }
        }

        // 转换ID类型，并查出这些ID对应的用户名
        List<Integer> userIds = new ArrayList<>(userIdSet);
        List<String> userNames = userMapper.getNamesByIds(userIds);

        // 构造NotifyAddDto
        NotifyAddDto notifyAddDto = new NotifyAddDto();
        notifyAddDto.setNotifyType(notifySendDto.getNotifyType());

        // 拼接任务名
        List<String> taskNames = taskMapper.getTaskNamesByIds(notifySendDto.getId());
        String res;
        if (taskNames.size() > 1) {
            StringBuilder sb = new StringBuilder();
            for (String name : taskNames) {
                sb.append(name).append("，");
            }
            res = sb.substring(0, sb.length() - 1);
        } else {
            res = taskNames.get(0);
        }

        // 插入数据库
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        NotifyMapper mapper = sqlSession.getMapper(NotifyMapper.class);
        try {
            // 循环插入
            for (int i = 0; i < userNames.size(); i++) {
                notifyAddDto.setContent(userNames.get(i) + " 你好，你的导师删除了 " + taskNames.size() + " 个任务：" + res);
                notifyAddDto.setUserId(userIds.get(i));
                mapper.insertForTypeAdd(notifyAddDto);
            }

            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("发生异常，事务回滚");
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    /*
     * 新增任务的情况的通知
     * */
    private void notifyTypeIsAdd (NotifySendDto notifySendDto) {
        // 转换ID类型
        String[] assignedUserIds = notifySendDto.getTaskAssignedUserId().split(",");
        List<Integer> userIds = new ArrayList<>(assignedUserIds.length);
        for (String s : assignedUserIds) {
            userIds.add(Integer.parseInt(s));
        }
        List<String> userNames = userMapper.getNamesByIds(userIds);

        // 构造NotifyAddDto
        NotifyAddDto notifyAddDto = new NotifyAddDto();
        notifyAddDto.setNotifyType(notifySendDto.getNotifyType());

        // 因为新增任务，任务名只会有一个
        String taskName = notifySendDto.getTaskName();

        // 插入到数据库
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        NotifyMapper mapper = sqlSession.getMapper(NotifyMapper.class);
        try {
            // 循环插入
            for (int i = 0; i < userNames.size(); i++) {
                notifyAddDto.setContent(userNames.get(i) + " 你好，你的导师发布了新任务：" + taskName);
                notifyAddDto.setUserId(userIds.get(i));
                mapper.insertForTypeAdd(notifyAddDto);
            }

            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("发生异常，事务回滚");
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    /*
     * 更新任务的情况的通知
     * */
    private void notifyTypeIsUpdate (NotifySendDto notifySendDto) {
        // 获取修改前的分配用户ID集合
        Set<Integer> beforeIds = Arrays.stream(notifySendDto.getBeforeAssignedUserId().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        // 获取当前分配用户ID集合
        Set<Integer> currentIds = Arrays.stream(notifySendDto.getTaskAssignedUserId().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());


        // 求修改前和修改后的id区别——发送删除的通知
        List<Integer> idsForDelete = beforeIds.stream()
                .filter(i -> !currentIds.contains(i))
                .collect(Collectors.toList());

        // 求 修改后比修改前 新增加了的ID——发送新增的通知
        List<Integer> idsForAdd = currentIds.stream()
                .filter(i -> !beforeIds.contains(i))
                .collect(Collectors.toList());

        // 求修改前和修改后的交集——发送修改的通知
        Set<Integer> intersectionSet = new HashSet<>(beforeIds);
        intersectionSet.retainAll(currentIds);
        List<Integer> idsForUpdate = new ArrayList<>(intersectionSet);


        /*
         * 获取需要发送不同类型通知的用户名
         * */
        // 删除类型通知的用户名
        List<String> namesForDelete = new ArrayList<>();
        if (!idsForDelete.isEmpty()) {
            namesForDelete = userMapper.getNamesByIds(idsForDelete);
        }
        // 新增类型通知的用户名
        List<String> namesForAdd = new ArrayList<>();
        if (!idsForAdd.isEmpty()) {
            namesForAdd = userMapper.getNamesByIds(idsForAdd);
        }
        // 更新类型通知的用户名
        List<String> namesForUpdate = userMapper.getNamesByIds(idsForUpdate);


        // 构造NotifyAddDto
        NotifyAddDto notifyAddDto = new NotifyAddDto();
        notifyAddDto.setNotifyType(notifySendDto.getNotifyType());

        // 因为是更新任务，任务名只会有一个
        String taskName = taskMapper.getTaskNamesByIds(notifySendDto.getId()).get(0);

        // 插入到数据库
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        NotifyMapper mapper = sqlSession.getMapper(NotifyMapper.class);
        try {
            // 循环插入
            for (int i = 0; i < namesForDelete.size(); i++) {
                notifyAddDto.setContent(namesForDelete.get(i) + " 你好，你的导师将你移除出了任务：" + taskName);
                notifyAddDto.setUserId(idsForDelete.get(i));
                mapper.insertForTypeAdd(notifyAddDto);
            }

            for (int i = 0; i < namesForAdd.size(); i++) {
                notifyAddDto.setContent(namesForAdd.get(i) + " 你好，你的导师将你添加到了任务：" + taskName);
                notifyAddDto.setUserId(idsForAdd.get(i));
                mapper.insertForTypeAdd(notifyAddDto);
            }

            for (int i = 0; i < namesForUpdate.size(); i++) {
                notifyAddDto.setContent(namesForUpdate.get(i) + " 你好，你的导师修改了任务：" + taskName);
                notifyAddDto.setUserId(idsForUpdate.get(i));
                mapper.insertForTypeAdd(notifyAddDto);
            }
            // 4 5 6-》1 2 6
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("发生异常，事务回滚");
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    /*
     * 监听发送邮件的消息
     * */
    @Override
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConstant.QUEUE_EMAIL, durable = "true"),
            exchange = @Exchange(name = MqConstant.EXCHANGE_EMAIL, type = ExchangeTypes.TOPIC),
            key = {MqConstant.ROUTING_KEY_EMAIL_ALL}
    ))
    public void sendEmail (NotifyEmailDto notifyEmailDto) {
        Integer emailType = notifyEmailDto.getEmailType();

        if (emailType == 1) {
            // 新增类型的邮件
            sendEmailForAdd(notifyEmailDto);
        } else if (emailType == 2) {
            // 删除类型的邮件
            sendEmailForDelete(notifyEmailDto);
        } else if (emailType == 3) {
            // 更新类型的邮件
            sendEmailForUpdate(notifyEmailDto);
        }
    }

    /*
     * 发送新增类型的邮件
     * */
    private void sendEmailForAdd (NotifyEmailDto notifyEmailDto) {
        // 转换ID类型
        String[] assignedUserIds = notifyEmailDto.getTaskAssignedUserId().split(",");
        List<Integer> userIds = new ArrayList<>(assignedUserIds.length);
        for (String s : assignedUserIds) {
            userIds.add(Integer.parseInt(s));
        }

        // 得到名字和邮件对应的对象
        List<NameAndEmailDto> nameAndEmails = userMapper.getNameAndEmailsByIds(userIds);
        List<String> emails = new ArrayList<>(nameAndEmails.size());

        // 新增任务时，任务名只会有一个
        String taskName = notifyEmailDto.getTaskName().get(0);

        // 构造content
        List<String> contents = new ArrayList<>(nameAndEmails.size());
        for (NameAndEmailDto nameAndEmail : nameAndEmails) {
            contents.add(nameAndEmail.getName() + " 你好，你的导师发布了新任务：" + taskName);
            emails.add(nameAndEmail.getEmail());
        }

        // 发送邮件
        mailService.sendSimpleMail(MailConstant.FROM, emails.toArray(new String[0]), MailConstant.SUBJECT_ADD, contents);
    }

    /*
     * 发送删除类型的邮件
     * */
    private void sendEmailForDelete (NotifyEmailDto notifyEmailDto) {
        // 通过消息中的任务主键ID，得到assignedUserIds
        List<Integer> taskIds = notifyEmailDto.getId();
        List<String> assignedUserIds = taskMapper.getTaskAssignedUserIds(taskIds);

        // 对assignedUserIds去重
        Set<Integer> userIdSet = new HashSet<>();
        for (String assignedUserId : assignedUserIds) {
            String[] ids = assignedUserId.split(",");
            for (String userId : ids) {
                userIdSet.add(Integer.parseInt(userId));
            }
        }

        // 转换ID类型，并查出这些ID对应的用户名和邮件
        List<Integer> userIds = new ArrayList<>(userIdSet);
        List<NameAndEmailDto> nameAndEmails = userMapper.getNameAndEmailsByIds(userIds);

        // 查出任务名并拼接
        List<String> taskNames = taskMapper.getTaskNamesByIds(taskIds);
        String res;
        if (taskNames.size() > 1) {
            StringBuilder sb = new StringBuilder();
            for (String name : taskNames) {
                sb.append(name).append("，");
            }
            res = sb.substring(0, sb.length() - 1);
        } else {
            res = taskNames.get(0);
        }

        // 构造content
        List<String> contents = new ArrayList<>(taskNames.size());
        List<String> emails = new ArrayList<>(nameAndEmails.size());
        for (NameAndEmailDto nameAndEmail : nameAndEmails) {
            contents.add(nameAndEmail.getName() + " 你好，你的导师删除了 " + taskNames.size() + " 个任务：" + res);
            emails.add(nameAndEmail.getEmail());
        }

        // 发送邮件
        mailService.sendSimpleMail(MailConstant.FROM, emails.toArray(new String[0]), MailConstant.SUBJECT_DELETE, contents);
    }

    /*
     * 发送修改类型的邮件
     * */
    private void sendEmailForUpdate (NotifyEmailDto notifyEmailDto) {

    }

    @Override
    public Page<NotifyListVo> list (NotifyListDto notifyListDto) {
        PageHelper.startPage(notifyListDto.getPageNum(), notifyListDto.getPageSize());
        List<NotifyListVo> list = notifyMapper.list(notifyListDto);
        return PageUtil.toPage(list);
    }

    @Override
    public NotifySingleVo getById (Integer id) {
        return notifyMapper.getById(id);
    }

    @Override
    public Integer delete (List<Integer> ids) {
        return notifyMapper.delete(ids);
    }

    @Override
    public Integer getIsNotLookCount () {
        return notifyMapper.getIsNotLookCount(UserUtil.get().getUserId());
    }
}
