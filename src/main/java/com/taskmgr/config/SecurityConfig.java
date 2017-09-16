package com.taskmgr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by Akai on 2017-04-03.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailService")
	UserDetailsService userDetailsService;


	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder authenticatior) throws Exception {
		authenticatior.userDetailsService(userDetailsService);
	}

	//TODO dodanie mapowania do security

	/**
	 * Security configuration
	 *
	 * @param http address of entry point of server
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new SimpleCorsFilter(), BasicAuthenticationFilter.class);
		http.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/project/**").access("hasRole('ADMIN') or hasRole('MANAGER')")
				.antMatchers("/user/**").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
				.and().logout().permitAll()
				// logowanie dla resta
				.and().httpBasic();
		//.and().exceptionHandling().accessDeniedPage("/Access_Denied");

		//TODO  teoretycznie to naprawy i dodanie obslugi csrf przez resta
		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll();

		//http.formLogin()
		//		.loginPage("/login").permitAll();

	}
}
