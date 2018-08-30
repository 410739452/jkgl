package com.jkgl.config.mybatis;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jkgl.admin.entity.User;

@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void insertFill(MetaObject metaObject) {
		logger.debug("插入方法实体填充");

		
		setFieldValByName(User.CREATE_USER, "PF", metaObject);//TUDO: 用户名需要从shiro中拿
		setFieldValByName(User.CREATE_TIME, new Timestamp(new Date().getTime()), metaObject);
		

		setFieldValByName(User.UPDATE_USER, "PF", metaObject);
		setFieldValByName(User.UPDATE_TIME, new Timestamp(new Date().getTime()), metaObject);

		setFieldValByName(User.DELETED, 0, metaObject);

	}

	@Override
	public void updateFill(MetaObject metaObject) {
		logger.debug("更新方法实体填充");

		setFieldValByName(User.UPDATE_USER, "PF", metaObject);
		setFieldValByName(User.UPDATE_TIME, new Timestamp(new Date().getTime()), metaObject);
	}
}
