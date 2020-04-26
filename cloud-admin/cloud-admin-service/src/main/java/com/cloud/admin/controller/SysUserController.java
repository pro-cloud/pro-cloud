package com.cloud.admin.controller;


import com.cloud.admin.beans.dto.RoleDTO;
import com.cloud.admin.beans.dto.UserDTO;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.admin.util.UserUtil;
import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.service.SysUserService;
import com.cloud.common.data.enums.ResultEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 用户表
 *
 * @author Aijm
 * @date 2019-08-25 20:20:58
 */
@RestController
@RequestMapping("/user" )
@Api(value = "user", tags = "sysuser管理")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 分页查询
     * @param page 分页对象 包含根据角色筛选
     * @param userDTO 用户表
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('admin_sysuser_view')")
    public Result getSysUserPage(Page page, UserDTO userDTO) {
        return Result.success(sysUserService.getSysUserPage(page, userDTO));
    }

    /**
     * 通过id查询用户表
     * @return Result
     */
    @GetMapping("/info")
    @PreAuthorize("@pms.hasPermission('admin_sysuser_view')")
    public Result getUserInfo() {
        UserDTO userDTO = UserUtil.getUserDTO();
        userDTO.setMenuList(UserUtil.getMenuList());
        return Result.success(userDTO);
    }

    /**
     * 通过id查询用户表
     * @param id id
     * @return Result
     */
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysuser_view')")
    public Result getById(@PathVariable("id") Long id) {
        UserDTO userDTO = UserUtil.getUserDTO(id);
        userDTO.setMenuList(UserUtil.getMenuList());
        return Result.success(userDTO);
    }

    /**
     * 新增用户表
     * @param userDTO 用户表
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_sysuser_add')")
    public Result save(@RequestBody @Valid UserDTO userDTO) {
        // 角色数据有效性验证，过滤不在授权内的角色
        getRoleDTO(userDTO);
        // 处理密码
        String encode = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encode);
        return Result.success(sysUserService.saveUserDTO(userDTO));
    }

    /**
     * 修改用户表
     * @param userDTO 用户表
     * @return Result
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('admin_sysuser_edit')")
    public Result updateById(@RequestBody @Valid UserDTO userDTO) {
        // 角色数据有效性验证，过滤不在授权内的角色
        getRoleDTO(userDTO);

        // 处理密码
        // 获取到老密码
        String oldEncode = passwordEncoder.encode(userDTO.getPassword());
        SysUser byId = sysUserService.getById(userDTO.getId());
        if (!byId.getPassword().equals(oldEncode)) {
            // 表名输入的原密码和数据库密码不一致
            return Result.error(ResultEnum.LOGIN_PASSWORD);
        }
        String encode = passwordEncoder.encode(userDTO.getNewPassword());
        userDTO.setPassword(encode);
        return Result.success(sysUserService.updateUserDTO(userDTO));
    }

    /**
     * 通过id删除用户表
     * @param id id
     * @return Result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('admin_sysuser_del')")
    public Result removeById(@PathVariable Long id) {
        return Result.success(sysUserService.removeUserDTO(id));
    }

    /**
     * 过滤不再权限内的角色信息
     * @param userDTO 传递的角色id
     * @return
     */
    private void getRoleDTO(UserDTO userDTO) {

        // 如果不为超级管理员 需要排除不在权限内的角色
        if (!UserUtil.hasAdmin()) {
            List<Long> roleIdList = userDTO.getRoleIdList();
            List<RoleDTO> roleList = Lists.newArrayList();
            for (RoleDTO r : UserUtil.getRoleList()) {
                if (roleIdList.contains(r.getId())) {
                    roleList.add(r);
                }
            }
            userDTO.setRoleList(roleList);
        }
    }


}
