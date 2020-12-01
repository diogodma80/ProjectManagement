package com.dma.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	// injects the bean from the WebConfig class
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	// Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		auth.jdbcAuthentication().dataSource(dataSource)
//			.withDefaultSchema() // created the database model to support authentication rules
//			.withUser("myUser").password("pass").roles("USER")
//			.and()
//			.withUser("taz").password("pass2").roles("USER")
//			.and()
//			.withUser("managerUser").password("pass3").roles("ADMIN");
		
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled from user_accounts where username = ?")
			.authoritiesByUsernameQuery("select username, role from user_accounts where username = ?")
			.dataSource(dataSource)
			.passwordEncoder(bCryptEncoder);
	}
	
	// Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// hasAuthority prepends "ROLE_" to the role in the database
		// hasRole is used when the role in the database is in the format 'ROLE_ADMIN'
		// hasAuthority is used when the role is in the format 'ADMIN'
		http.authorizeRequests()
		.antMatchers("/projects/new").hasRole("ADMIN")
		.antMatchers("/projects/save").hasRole("ADMIN")
		.antMatchers("/employees/new").hasRole("ADMIN")
		.antMatchers("/employees/save").hasRole("ADMIN")
		.antMatchers("/", "/**").permitAll()
		.and()
		.formLogin(); // formLogin method calls the default login page
		
		// Spring Security provided default csrf protection
		// http.csrf().disable();
		// http.headers().frameOptions().disable();
	}

//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

}
