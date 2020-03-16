package com.cloud.admin.api;

import com.cloud.admin.beans.dto.DictDTO;
import com.cloud.admin.interfaces.DictService;
import com.cloud.admin.service.SysDictListService;
import com.cloud.admin.service.SysDictTreeService;
import com.cloud.common.data.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Aijm
 * @Description  暴露字典接口
 * @Date 2019/9/10
 */
@RestController
public class DictController implements DictService {


    @Autowired
    private SysDictTreeService sysDictTreeService;
    @Autowired
    private SysDictListService sysDictListService;


    @Override
    public Result getByType(@PathVariable("typeCode") String typeCode, @PathVariable("type") String type) {
        if (DictDTO.DICT_LIST.equals(type)) {
            return Result.success(sysDictListService.getDictListByType(typeCode));
        }
        return Result.success(sysDictTreeService.getDicTreeByType(typeCode));
    }
}
