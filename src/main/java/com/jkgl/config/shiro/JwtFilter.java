package com.jkgl.config.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtFilter extends BasicHttpAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

	/**
	 * 如果带有 token，则对 token 进行检查，否则直接通过
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws UnauthorizedException {
		// 判断请求的请求头是否带上 "Token"
		if (isLoginAttempt(request, response)) {
			// 如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
			try {
				executeLogin(request, response);
				return true;
			} catch (Exception e) {
				// token 错误
				responseError(request, response);
			}
		}
		// 如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
		return true;
	}

	/**
	 * 执行登陆操作
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader("Token");
		JwtToken jwtToken = new JwtToken(token);
		// 提交给realm进行登入，如果错误他会抛出异常并被捕获
		getSubject(request, response).login(jwtToken);
		// 如果没有抛出异常则代表登入成功，返回true
		return true;
	}
	
	/**
	 * 判断用户是否想要登入。 检测 header 里面是否包含 Token 字段
	 */
	@Override
	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		String token = req.getHeader("Token");
		return token != null;
	}	

    private void responseError(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/sys/unauthorized");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
