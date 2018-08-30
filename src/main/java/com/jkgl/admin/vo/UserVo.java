package com.jkgl.admin.vo;

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
public class UserVo {
	private String id;
	private String username;
	private String password;

}
