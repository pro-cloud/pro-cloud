package com.cloud.message.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.common.data.var.Global;
import com.cloud.common.util.base.Result;
import com.cloud.common.util.exception.BaseException;

import com.cloud.message.beans.dto.SendMsgDTO;
import com.cloud.message.beans.dto.SmsConfigDTO;
import com.cloud.message.beans.interfaces.SmsService;
import com.cloud.message.beans.po.SysMsg;
import com.cloud.message.beans.po.SysMsgConfig;
import com.cloud.message.service.SysMsgConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @Author Aijm
 * @Description 发送短信
 * @Date 2019/9/10
 */
@RestController
@Slf4j
public class SmsController implements SmsService {

    @Autowired
    private SysMsgConfigService sysMsgConfigService;

    /**
     * 为了复用 没有写死模板code 和对应的 参数
     * @param sendMsg
     * @return
     */
    @Override
    public Result infoByName(@RequestBody @Valid SendMsgDTO sendMsg) {

        SysMsgConfig config = sysMsgConfigService.getOne(Wrappers.emptyWrapper());
        if (config == null) {
            throw new BaseException("缺少短信平台配置.");
        }
        String templateCode = sendMsg.getTemplateCode();
        String sender = StringUtils.defaultIfBlank(sendMsg.getSender(), config.getSenderName());

        DefaultProfile profile = DefaultProfile.getProfile("default" ,config.getSmsPlatAccount(),
                config.getSmsPlatPassword());

        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(config.getSmsPlatUrl());
        request.setAction(SmsConfigDTO.SEND_SMS);
        request.setVersion(SmsConfigDTO.SMS_VSERSION);

        request.putQueryParameter("PhoneNumbers", sendMsg.getMobile());
        request.putQueryParameter("RegionId", SmsConfigDTO.SMS_REGION_ID);

        request.putQueryParameter("SignName", sender);
        request.putQueryParameter("TemplateCode", templateCode);
        if (StrUtil.isNotBlank(sendMsg.getParams())) {
            request.putQueryParameter("TemplateParam", sendMsg.getParams());
        }
        SysMsg record = new SysMsg();
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject json = JSONUtil.parseObj(response.getData());
            record.setRequestId(json.getStr("RequestId"));
            record.setType(sendMsg.getBizType());
            record.setMobile(sendMsg.getMobile());
            record.setContent(sendMsg.getParams());
            if (SmsConfigDTO.SMS_CODE_OK.equals(json.getStr("Code"))) {
                record.setSendState(Integer.valueOf(Global.YES));
            } else {
                record.setSendState(Integer.valueOf(Global.NO));
            }

            log.info(" 发送短信->{}","成功");
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
        return Result.success(record);
    }




}
