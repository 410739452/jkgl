package com.jkgl.admin.controller;

import java.io.UnsupportedEncodingException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jkgl.admin.entity.User;
import com.jkgl.admin.form.UserForm;
import com.jkgl.admin.service.UserService;
import com.jkgl.common.BaseController;
import com.jkgl.common.RestResult;
import com.jkgl.common.ValidationGroups;
import com.jkgl.util.JwtUtil;
import com.jkgl.util.PwdUtil;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/sys")
public class SysController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RestResult<String> login(@Validated({ ValidationGroups.Login.class }) UserForm userForm) {

		User user = userService.getOne(new QueryWrapper<User>().eq(User.USERNAME, userForm.getUsername()));

		String realPassword = PwdUtil.encryptPassword(Sha512Hash.ALGORITHM_NAME, userForm.getPassword(),
				user.getSalt(), 2);

		if (user == null || !realPassword.equals(user.getPassword())) {
			return fail("用户名或密码不正确");
		} else {
			return success(JwtUtil.sign(user.getId(), user.getPassword()));
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@RequiresAuthentication
	public RestResult<String> logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return success();
	}

	@RequestMapping(value = "/unauthorized/{message}", method = RequestMethod.GET)
	@ApiIgnore
	public RestResult<String> unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
		return fail(message);
	}

}
