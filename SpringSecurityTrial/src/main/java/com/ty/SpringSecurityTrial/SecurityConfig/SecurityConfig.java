package com.ty.SpringSecurityTrial.SecurityConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ty.SpringSecurityTrial.Service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	MyUserDetailsService detailsService;

	@Autowired
	DataSource dataSource;
	
	@Autowired
	private JwtFilter JwtFilter;

	@Bean
	public SecurityFilterChain defaultsecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(request) -> request.requestMatchers("User/hello","User/login")
				.permitAll()
				.requestMatchers("User/adminViewAllUsers").hasRole("ADMIN")
				.anyRequest()
				.authenticated());
		http.httpBasic(Customizer.withDefaults());
		http.formLogin(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable());
		http.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);
		//addfilterbefore is used to use other filter in our case JwtFilter and later some other filter UsernamePasswordAuthenticationFilter.class
		return http.build();

	}

//	@Bean
//	public UserDetailsService UserDetailsService() {
//		PasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
//		UserDetails user1 = User.withUsername("user").password("{noop}user@123").roles("USER").build();
//		UserDetails admin1 = User.withUsername("admin").password("{noop}admin@123").roles("ADMIN").build();
//
//		UserDetails user1 = User.withUsername("user").password(PasswordEncoder.encode("user@123")).roles("USER")
//				.build();
//		UserDetails admin1 = User.withUsername("admin").password(PasswordEncoder.encode("admin@123")).roles("ADMIN")
//			.build();
//		
//		//JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
//		
//		return new InMemoryUserDetailsManager(user1, admin1);
//	}

//	@Bean
//	public PasswordEncoder Passwordencoder() {
//		return new BCryptPasswordEncoder();
//	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provide = new DaoAuthenticationProvider();
		provide.setPasswordEncoder(new BCryptPasswordEncoder(12));
		//the above sentence is use when we want to validate that the encoded is password is correctly used or not inshort it decrypt the encoded password and then matches with the login form
//		provide.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //this is used when we dont want to use any encoded password 
		provide.setUserDetailsService(detailsService);
		return provide;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();

	}

}
