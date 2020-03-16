package com.cloud.message.beans.interfaces;



import com.cloud.common.data.base.Result;
import com.cloud.message.beans.dto.SendMsgDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


/**
 * 用户信息
 * @author Aijm
 * @since 2019/5/11
 */
public interface SmsService {

    /**
     * 发送短信
     * @param sendMsgDTO
     * @return
     */
    @RequestMapping(value="/sms/sendMsg", method= RequestMethod.POST)
    public Result infoByName(@RequestBody @Valid SendMsgDTO sendMsgDTO);

}
