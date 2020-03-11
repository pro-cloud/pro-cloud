package com.cloud.message.api;



import com.cloud.common.util.client.CloudServiceList;
import com.cloud.message.beans.interfaces.SmsService;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 用户信息
 * @author Aijm
 * @since 2019/5/11
 */
@FeignClient(value = CloudServiceList.CLOUD_MESSAGE)
public interface ISmsService extends SmsService {


}
