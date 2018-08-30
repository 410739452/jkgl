package com.jkgl.common;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

/**
 * 
 * <p>
 * 通用Entity
 * </p>
 *
 * @author PF
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@FieldNameConstants
public class BaseEntity<T extends Model<T>> extends Model<T> {
	
	private static final long serialVersionUID = -5054828250422001743L;

	@TableId("id")
//	@TableField(fill = FieldFill.INSERT)
	private String id;
	
	/**
	 * 添加用户时自动创建用户和时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createUser;
	@TableField(fill = FieldFill.INSERT)
	private Timestamp createTime;
	
	/**
	 * 更新数据时自动更新用户和时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updateUser;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Timestamp updateTime;
	
	/**
	 * 删除数据时自动修改状态，添加数据时自动添加状态
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;
	
	/**
	 * 使用 ActiveRecord 方式必须加上此方法
	 */
	@Override
	protected Serializable pkVal() {
		return id;
	}
}
