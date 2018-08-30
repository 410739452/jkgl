package com.jkgl.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkgl.admin.entity.User;
import com.jkgl.admin.form.UserForm;
import com.jkgl.admin.service.UserService;
import com.jkgl.admin.vo.UserVo;
import com.jkgl.common.BaseController;
import com.jkgl.common.RestResult;
import com.jkgl.common.validation.AddGroup;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@PostMapping("/add")
	public RestResult<UserVo> add(@Validated({ AddGroup.class }) UserForm userForm) {
		User user = new User();
		user.setUsername(userForm.getUsername()).setPassword(userForm.getPassword());
		user.insert();

		UserVo userVo = new UserVo();
		userVo.setId(user.getId()).setUsername(user.getUsername());

		return success(userVo);
	}

}
