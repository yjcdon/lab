package com.lab.service.impl;

import com.lab.constant.MqConstant;
import com.lab.dto.*;
import com.lab.mapper.NotifyMapper;
import com.lab.mapper.TaskMapper;
import com.lab.mapper.UserMapper;
import com.lab.response.Page;
import com.lab.service.NotifyService;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Resource
    private NotifyMapper notifyMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TaskMapper taskMapper;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /*
     * 监听增删改任务发送的消息，并插入notify表
     * */
    @Override
    public String add (NotifySendDto notifySendDto) {
        Integer notifyType = notifySendDto.getNotifyType();

        // 通知类型是新增任务
        if (notifyType == 1) {
            notifyTypeIsAdd(notifySendDto);
        } else if (notifyType == 2) {
            notifyTypeIsDelete(notifySendDto);
        } else if (notifyType == 3) {

        }

        return "";
    }

    /*
     * 删除任务的情况的通知
     * */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConstant.QUEUE_LAB, durable = "true"),
            exchange = @Exchange(name = MqConstant.EXCHANGE_LAB, type = ExchangeTypes.TOPIC),
            key = {MqConstant.ROUTING_KEY_DELETE}
    ))
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
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    /*
     * 新增任务的情况的通知
     * */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConstant.QUEUE_LAB, durable = "true"),
            exchange = @Exchange(name = MqConstant.EXCHANGE_LAB, type = ExchangeTypes.TOPIC),
            key = {MqConstant.ROUTING_KEY_ADD}
    ))
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
        String taskName = taskMapper.getTaskNamesByIds(notifySendDto.getId()).get(0);

        // 插入到数据库
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        NotifyMapper mapper = sqlSession.getMapper(NotifyMapper.class);
        try {
            // 循环插入
            for (int i = 0; i < userNames.size(); i++) {
                notifyAddDto.setContent(userNames.get(i) + " 你好，你的导师发布了新任务：" + taskName + "，请尽快处理。");
                notifyAddDto.setUserId(userIds.get(i));
                mapper.insertForTypeAdd(notifyAddDto);
            }

            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("发生异常，事务回滚");
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    private List<String> getUsernameList (String[] assignedUserIds) {
        List<Integer> userIds = new ArrayList<>(assignedUserIds.length);
        for (String s : assignedUserIds) {
            userIds.add(Integer.parseInt(s));
        }
        return userMapper.getNamesByIds(userIds);
    }

    @Override
    public Page<NotifyListVo> list (NotifyListDto notifyListDto) {
        return null;
    }

    @Override
    public NotifySingleVo getById (NotifySingleDto notifySingleDto) {
        return null;
    }

    @Override
    public Integer delete (List<Integer> ids) {
        return 0;
    }

    @Override
    public String sendEmail (NotifyEmailDto notifyEmailDto) {
        return "";
    }
}
