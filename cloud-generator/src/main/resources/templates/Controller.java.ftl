package ${package}.${moduleName}.controller;

import com.cloud.common.util.base.Result;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package}.${moduleName}.beans.po.${className};
import ${package}.${moduleName}.service.${className}Service;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * ${comments}
 *
 * @author ${author}
 * @date ${datetime}
 */
@RestController
@RequestMapping("/${pathName}" )
@Api(value = "${pathName}", tags = "${pathName}管理")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${classname}Service;

    /**
     * 分页查询
     * @param page 分页对象
     * @param ${classname} ${comments}
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_view')")
    public Result get${className}Page(Page page, ${className} ${classname}) {
        return Result.success(${classname}Service.page(page, Wrappers.query(${classname})));
    }


    /**
     * 通过id查询${comments}
     * @param ${pk.lowerAttrName} id
     * @return Result
     */
    @GetMapping("/{${pk.lowerAttrName}}")
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_view')")
    public Result getById(@PathVariable("${pk.lowerAttrName}") ${pk.attrType} ${pk.lowerAttrName}) {
        return Result.success(${classname}Service.getById(${pk.lowerAttrName}));
    }

    /**
     * 新增${comments}
     * @param ${classname} ${comments}
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_add')")
    public Result save(@RequestBody @Valid ${className} ${classname}) {
        return Result.success(${classname}Service.save(${classname}));
    }

    /**
     * 修改${comments}
     * @param ${classname} ${comments}
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_edit')")
    public Result updateById(@RequestBody @Valid ${className} ${classname}) {
        return Result.success(${classname}Service.updateById(${classname}));
    }

    /**
     * 通过id删除${comments}
     * @param ${pk.lowerAttrName} id
     * @return Result
     */
    @DeleteMapping("/{${pk.lowerAttrName}}")
    @PreAuthorize("@pms.hasPermission('${moduleName}_${pathName}_del')")
    public Result removeById(@PathVariable ${pk.attrType} ${pk.lowerAttrName}) {
        return Result.success(${classname}Service.removeById(${pk.lowerAttrName}));
    }

}
