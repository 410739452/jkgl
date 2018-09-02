package com.jkgl.config.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jkgl.admin.entity.User;
import com.jkgl.admin.service.UserService;
import com.jkgl.util.JwtUtil;

@Component
public class MyRealm extends AuthorizingRealm {

	@Autowired
	UserService userService;

	/**
	 * 必须重写此方法，不然Shiro会报错
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		String token = (String) auth.getCredentials();

		String userid = JwtUtil.getUserid(token);
		if (userid == null) {
			throw new AuthenticationException("非法的Token");
		}

		User user = userService.getById(userid);
		if (user == null) {
			throw new AuthenticationException("用户不存在");
		}

		if (!JwtUtil.verify(token, userid, user.getPassword())) {
			throw new AuthenticationException("用户名或密码不正确");
		}

		return new SimpleAuthenticationInfo(token, token, getName());
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = userService.getOne(new QueryWrapper<User>().eq(User.USERNAME, username));

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		Set<String> roles = new HashSet<>();
		roles.add("user");
//		roles.add("admin");

		Set<String> permissions = new HashSet<>();
		permissions.add("list");
//		permissions.add("add");
//		permissions.add("edit");

		info.addRoles(roles);
		info.addStringPermissions(permissions);

		return info;
	}
}
