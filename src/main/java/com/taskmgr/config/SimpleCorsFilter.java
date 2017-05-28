package com.taskmgr.config;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Akai on 2017-05-24.
 * To jest tak magiczne ze nie wiem co sie dzieje i czemu
 */
@Component
public class SimpleCorsFilter implements Filter {


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		//headery sa potrzebne bo inaczej nie mo komunikacji pomiedzy serwerami
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, authorization, x-auth-token, access-control-allow-origin");

		//potrzebne do logina ktory leci przez option nie przez post
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
		//chain.doFilter(req, res);

	}


	public void init(FilterConfig filterConfig) {
	}


	public void destroy() {
	}
}