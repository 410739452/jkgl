package com.jkgl.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jkgl.common.BaseEntity;

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

	private String username;
	private String password;

}
