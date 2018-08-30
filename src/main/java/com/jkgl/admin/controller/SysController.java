package com.jkgl.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkgl.admin.form.UserForm;
import com.jkgl.admin.service.UserService;
import com.jkgl.common.BaseController;
import com.jkgl.common.RestResult;
import com.jkgl.common.validation.LoginGroup;

@RestController
@RequestMapping("/sys")
public class SysController extends BaseController {
	
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public RestResult<String> login(@Validated({ LoginGroup.class }) UserForm userForm) {
		return success("登录成功");
	}
	
}
