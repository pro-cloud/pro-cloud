package com.cloud.common.oauth.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * UserDetails 配置
 * @author Aijm
 * @since 2019/6/9
 */
public class SecurityUser extends User {


	/**
	 * 存储用户的id 为了方便查询用户详细信息
	 */
	private Long userId;

	/**
	 * 存储用户的姓名
	 */
	private String name;

	/**
	 * 用户类型
	 */
	private String userType;

	/**
	 * 能够管理的租户
	 */
	private String tenantIds;

	public SecurityUser(String username, String password, Long userId, String userType,
						String name, String tenantIds) {
		super(username, password, AuthorityUtils.NO_AUTHORITIES);
		this.userId = userId;
		this.userType = userType;
		this.name = name;
		this.tenantIds = tenantIds;
	}
	public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getTenantIds() {
		return tenantIds;
	}

	public void setTenantIds(String tenantIds) {
		this.tenantIds = tenantIds;
	}
}
