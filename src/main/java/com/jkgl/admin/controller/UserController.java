package com.jkgl.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.jkgl.admin.entity.User;
import com.jkgl.admin.service.UserService;
import com.jkgl.common.BaseController;
import com.jkgl.common.RestAssert;
import com.jkgl.common.RestErrorCode;
import com.jkgl.common.RestResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api("用户接口")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	@ApiOperation(value = "用户登录", notes = "用户登录接口")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
		@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String") 
	})
	@ApiResponses({
		@ApiResponse(code=0, message="失败"),
		@ApiResponse(code=1, message="成功"),
	})
	public RestResult<String> testError(String username, String password) {
		RestAssert.notNull(RestErrorCode.FAILED, username, password);
		return success("登录成功");
	}

	@PostMapping("/add")
	public RestResult<User> add(User user) {
		JSON.toJSONString(user);

		user.insert();
		return success(user);
	}

}
