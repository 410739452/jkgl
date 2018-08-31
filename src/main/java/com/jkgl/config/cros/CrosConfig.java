package com.jkgl.config.cros;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * <p>
 * 跨域资源共享(CROS)配置
 * </p>
 *
 * @author PF
 *
 */
@Configuration
public class CrosConfig {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")// 设置服务器接收所有域名的请求
						.allowedMethods("*")// 设置服务器支持所有的请求方法
						.allowedHeaders("*")// 设置服务器支持所有请求头信息字段
						//.exposedHeaders("") // 设置客户端可以读取那些头信息字段
						.allowCredentials(false) // 设置客户端是否给服务端发送Cookie
						.maxAge(3600); // 设置预检请求的有效期，单位为秒
			}
		};
	}

}
