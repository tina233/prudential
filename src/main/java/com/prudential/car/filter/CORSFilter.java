package com.prudential.car.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:qutingting
 * @Description: CORS跨域处理, 解决前端域名与后端域名不一致时跨域调用问题
 */
@WebFilter(filterName = "corsFilter", urlPatterns = "/*",
		initParams = {
				// 忽略资源
				@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico")
		})
public class CORSFilter extends OncePerRequestFilter {
	/**
	 * 跨域处理
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,HEAD,DELETE,TRACE,PATCH");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Max-Age", "3600");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, Cache-Control, Accept, Origin, X-Session-ID, content-type");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", "-1");
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			return;
		}
		filterChain.doFilter(request, response);
	}
}
