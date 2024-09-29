package com.lab.service;

import com.lab.dto.NotifyEmailDto;
import com.lab.dto.NotifyListDto;
import com.lab.dto.NotifySendDto;
import com.lab.response.Page;
import com.lab.vo.NotifyListVo;
import com.lab.vo.NotifySingleVo;

import java.util.List;

public interface NotifyService {
    void add (NotifySendDto notifySendDto);

    Page<NotifyListVo> list (NotifyListDto notifyListDto);

    NotifySingleVo getById (Integer id);

    Integer delete (List<Integer> ids);

    String sendEmail (NotifyEmailDto notifyEmailDto);

    Integer getIsNotLookCount ();
}
