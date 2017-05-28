package com.taskmgr.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Akai on 2017-05-24.
 */
//TODO sprawdzic czty to jest wogole potrzebne
@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint
		implements AuthenticationEntryPoint {


	public void commence(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}