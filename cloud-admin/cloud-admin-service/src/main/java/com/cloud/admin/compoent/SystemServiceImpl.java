package com.cloud.admin.compoent;

import com.cloud.admin.util.UserUtil;
import com.cloud.common.data.user.SystemService;
import org.springframework.stereotype.Service;
/**
 * @Author Aijm
 * @Description  提供datam模块使用 获取到用户的id
 *      data 对每个对象进行数据库插入或者更新时的操作
 * @Date 2019/9/4
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Override
    public Long getUserId() {
        return UserUtil.getUserId();
    }
}
