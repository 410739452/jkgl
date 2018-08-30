package com.jkgl.admin.entity;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jkgl.common.BaseEntity;
import com.jkgl.common.validation.AddGroup;
import com.jkgl.common.validation.UpdateGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@TableName("\"user\"")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class User extends BaseEntity<User> {

	private static final long serialVersionUID = -2414704754314742291L;

	@NotBlank(message = "用户名不能为空", groups = { AddGroup.class })
	private String username;

	@NotBlank(message = "密码不能为空", groups = { AddGroup.class, UpdateGroup.class })
	private String password;

}
