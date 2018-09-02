package com.jkgl.admin.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Sha512Hash;
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
import com.jkgl.common.ValidationGroups;
import com.jkgl.util.PwdUtil;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@PostMapping("/add")
	@RequiresRoles("admin")
//	@RequiresPermissions(logical = Logical.AND, value = {"add", "edit"})
	public RestResult<UserVo> add(@Validated({ ValidationGroups.Add.class }) UserForm userForm) {
		User user = new User();
		user.setUsername(userForm.getUsername());
		user.setSalt(PwdUtil.generateSalt(20));
		user.setPassword(PwdUtil.encryptPassword(Sha512Hash.ALGORITHM_NAME, userForm.getPassword(), user.getSalt(), 2));
		user.insert();

		UserVo userVo = new UserVo();
		userVo.setId(user.getId()).setUsername(user.getUsername());

		return success(userVo);
	}

}
