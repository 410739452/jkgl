package com.jkgl.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkgl.admin.entity.User;
import com.jkgl.admin.service.UserService;
import com.jkgl.common.BaseController;
import com.jkgl.common.RestAssert;
import com.jkgl.common.RestErrorCode;
import com.jkgl.common.RestResult;
import com.jkgl.common.validation.AddGroup;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public RestResult<String> login(String username, String password) {
		RestAssert.notNull(RestErrorCode.FAILED, username, password);
		return success("登录成功");
	}

	@PostMapping("/add")
	public RestResult<User> add(@Validated({ AddGroup.class }) User user) {
		user.insert();
		return success(user);
	}

}
