package com.lab.service.impl;

import com.lab.constant.MqConstant;
import com.lab.dto.*;
import com.lab.mapper.NotifyMapper;
import com.lab.response.Page;
import com.lab.service.NotifyService;
import com.lab.vo.NotifyListVo;
import com.lab.vo.NotifySingleVo;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Resource
    private NotifyMapper notifyMapper;

    @Resource
    private RabbitListener rabbitListener;

    /*
     * 监听增删改任务发送的消息，并插入notify表
     * */
    @Override
    public String add (NotifySendDto notifySendDto) {
        addReal(notifySendDto);
        return "";
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MqConstant.QUEUE_LAB, durable = "true"),
            exchange = @Exchange(name = MqConstant.EXCHANGE_LAB, type = ExchangeTypes.TOPIC),
            key = {MqConstant.ROUTING_KEY_ADD,
                    MqConstant.ROUTING_KEY_DELETE,
                    MqConstant.ROUTING_KEY_UPDATE}
    ))
    private static void addReal (NotifySendDto notifySendDto) {
        List<Integer> taskIds = notifySendDto.getId();

        List<Integer> userIds = new ArrayList<>();
        for (String s : notifySendDto.getTaskAssignedUserId().split(",")) {
            userIds.add(Integer.parseInt(s));
        }

        NotifyAddDto notifyAddDto = new NotifyAddDto();
        notifyAddDto.setUserId(userIds);
        notifyAddDto.setNotifyType(notifySendDto.getNotifyType());
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
