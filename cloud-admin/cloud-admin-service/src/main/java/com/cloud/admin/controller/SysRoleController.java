package com.cloud.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.beans.dto.RoleDTO;
import com.cloud.admin.util.UserUtil;
import com.cloud.common.data.base.Result;
import com.cloud.admin.beans.po.SysRole;
import com.cloud.admin.service.SysRoleService;
import com.cloud.common.data.enums.ResultEnum;
import com.cloud.common.util.util.StrUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 角色表
 *
 * @author Aijm
 * @date 2019-08-25 20:57:31
 */
@RestController
@RequestMapping("/role" )
@Api(value = "role", tags = "sysrole管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 查询 全部 角色
     * @return
     */
    @GetMapping("/listALL")
    @PreAuthorize("@pms.hasPermission('admin_sysrole_view')")
    @ApiOperation(value = "查询用户拥有的角色", notes = "查询用户拥有的角色信息")
    public Result getSysRoleAll() {
        return Result.success(UserUtil.getRoleAll());
    }

    /**
     * 分页查询
     * @param page
     * @param roleDTO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('admin_sysuser_view')")
    public Result getSysRolePage(Page page, RoleDTO roleDTO) {
        return Result.success(sysRoleService.page(page, Wrappers.<SysRole>query().lambda()
                .like(StrUtils.isNotBlank(roleDTO.getName()),SysRole:: getName , roleDTO.getName())
                .orderByAsc(SysRole::getUpdateDate)));
    }

    /**
     * 通过id查询角色表
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysrole_view')")
    public Result getById(@PathVariable("id") Long id) {
        return Result.success(sysRoleService.get(id));
    }

    /**
     * 新增角色表
     * @param roleDTO 角色表
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_sysrole_add')")
    public Result save(@RequestBody @Valid RoleDTO roleDTO) {
        // 判断数据是否有操作权限
        if (chealDataScopeAdd(roleDTO)){
            return Result.error(ResultEnum.CRUD_NOT_OPERATE);
        }
        return Result.success(sysRoleService.saveRoleDTO(roleDTO));
    }

    /**
     * 修改角色表
     * @param roleDTO 角色表
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin_sysrole_edit')")
    public Result updateById(@RequestBody @Valid RoleDTO roleDTO) {
        // 判断数据是否有操作权限
        SysRole byId = sysRoleService.getById(roleDTO.getId());
        if (chealDataScopeUpdate(byId)){
            return Result.error(ResultEnum.CRUD_NOT_OPERATE);
        }
        return Result.success(sysRoleService.updateByRole(roleDTO));
    }

    /**
     * 通过id删除角色表
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysrole_del')")
    public Result removeById(@PathVariable Long id) {
        // 判断数据是否有操作权限
        SysRole byId = sysRoleService.getById(id);
        if (chealDataScopeUpdate(byId)){
            return Result.error(ResultEnum.CRUD_NOT_OPERATE);
        }
        return Result.success(sysRoleService.removeRoleById(id));
    }


    /**
     * 判断是否具备添加数据权限
     * @param sysRole
     * @return
     */
    private boolean chealDataScopeAdd(SysRole sysRole) {
        if (!UserUtil.hasAdmin()) {
            int maxDataScope = UserUtil.getMaxDataScope();
            // 添加的数据权限大于自己本身
            if (maxDataScope > sysRole.getDataScope()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否具备修改/删除数据权限
     * @param sysRole 当前操作角色的数据库存储信息
     * @return
     */
    private boolean chealDataScopeUpdate(SysRole sysRole) {
        if (!UserUtil.hasAdmin()) {
            int maxDataScope = UserUtil.getMaxDataScope();
            // 添加的数据权限大于自己本身
            if (maxDataScope > sysRole.getDataScope()) {
                // 判断 是否
                List<RoleDTO> roleList = UserUtil.getRoleList();
                for (RoleDTO roleDTO : roleList) {
                    if (roleDTO.getId().equals(sysRole.getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
