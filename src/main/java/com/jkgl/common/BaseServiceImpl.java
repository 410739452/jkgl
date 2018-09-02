package com.jkgl.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 
 * <p>
 * 通用服务层实现
 * </p>
 *
 * @author PF
 *
 * @param <M> Mapper类型
 * @param <E> Entity类型
 */
public class BaseServiceImpl<M extends BaseMapper<E>, E> extends ServiceImpl<M, E> {

}