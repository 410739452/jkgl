package com.jkgl.admin.form;

import javax.validation.constraints.NotBlank;

import com.jkgl.common.ValidationGroups;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class UserForm {

	@NotBlank(message = "用户名不能为空", groups = { ValidationGroups.Login.class, ValidationGroups.Add.class })
	private String username;

	@NotBlank(message = "密码不能为空", groups = { ValidationGroups.Login.class, ValidationGroups.Add.class, ValidationGroups.Update.class })
	private String password;

}
