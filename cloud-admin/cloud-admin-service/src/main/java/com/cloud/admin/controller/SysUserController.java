package com.cloud.admin.controller;


import com.cloud.admin.beans.dto.RoleDTO;
import com.cloud.admin.beans.dto.UserDTO;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.admin.util.UserUtil;
import com.cloud.common.data.base.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.service.SysUserService;
import com.cloud.common.data.enums.ResultEnum;
import com.cloud.common.util.util.StrUtils;
import com.cloud.common.util.var.StaticVar;
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
     *  只需要登录后就能访问
     * @return Result
     */
    @GetMapping("/info")
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
        return Result.success(UserUtil.getUserDTO(id));
    }

    /**
     * 新增用户表
     * @param userDTO 用户表
     * @return Result
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('admin_sysuser_add')")
    public Result save(@RequestBody @Valid UserDTO userDTO) {
        // 校验是否能够注册邮箱或者手机号或者登录名
        if(sysUserService.getCheckUserDTO(userDTO)) {
            return Result.error(ResultEnum.PARAM_REGISTER);
        }

        // 角色数据有效性验证，过滤不在授权内的角色
        getRoleDTO(userDTO);
        // 处理密码
        if (StrUtils.isBlank(userDTO.getPassword())) {
            userDTO.setPassword(StaticVar.DEFAULT_USER_PASSWORD);
        }
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
        // 校验是否能够注册邮箱或者手机号或者登录名
        if(sysUserService.getCheckUserDTO(userDTO)) {
            return Result.error(ResultEnum.PARAM_REGISTER);
        }

        // 角色数据有效性验证，过滤不在授权内的角色
        getRoleDTO(userDTO);

        // 处理密码 密码不为空 需要修改密码信息
        if (StrUtils.isNotBlank(userDTO.getPassword())) {
            String encode = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encode);
        }
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
     * 修改密码信息
     * @param userDTO 用户表
     * @return Result
     */
    @PutMapping("updatePassword")
    @PreAuthorize("@pms.hasPermission('admin_sysuser_updatePassword')")
    public Result updatePassword(@RequestBody @Valid UserDTO userDTO) {
        // 获取到老密码
        String oldEncode = passwordEncoder.encode(userDTO.getPassword());
        SysUser byId = UserUtil.getUser();
        if (!byId.getPassword().equals(oldEncode)) {
            // 表名输入的原密码和数据库密码不一致
            return Result.error(ResultEnum.LOGIN_PASSWORD);
        }
        String encode = passwordEncoder.encode(userDTO.getNewPassword());
        userDTO.setPassword(encode);
        return Result.success(sysUserService.updateById(userDTO));
    }

    /**
     * 校验用户是否能注册获取修改
     * @param userDTO
     * @return
     */
    @PutMapping("/check")
    @PreAuthorize("@pms.hasPermission('admin_sysuser_edit')")
    public Result checkUser(@RequestBody @Valid UserDTO userDTO) {
        // 查看是否注册过
        if (sysUserService.getCheckUserDTO(userDTO)) {
            return Result.error("登录名或者手机号或者密码已被注册!");
        }
        return Result.success("");
    }


    /**
     * 过滤不再权限内的角色信息
     * @param userDTO 传递的角色id
     * @return
     */
    private void getRoleDTO(UserDTO userDTO) {

        List<Long> roleIdList = userDTO.getRoleIdList();
        List<RoleDTO> roleList = Lists.newArrayList();
        // 是超级管理员
        if (UserUtil.hasAdmin()) {
            for (Long id : roleIdList) {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setId(id);
                roleList.add(roleDTO);
            }
        }
        // 不为超级管理员
        else {
            for (RoleDTO r : UserUtil.getRoleList()) {
                if (roleIdList.contains(r.getId())) {
                    roleList.add(r);
                }
            }
        }
        userDTO.setRoleList(roleList);

    }




}
