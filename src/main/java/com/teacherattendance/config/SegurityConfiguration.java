package com.teacherattendance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SegurityConfiguration {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(csrf -> csrf
					.disable())
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(
				"/registro**",
				"/js/**",
				"/css/**",
				"/img/**").permitAll()
				.requestMatchers(HttpMethod.GET).permitAll()
				.requestMatchers(HttpMethod.OPTIONS).permitAll()
<<<<<<< Updated upstream
				.requestMatchers("/auth/**").permitAll()
=======
				.requestMatchers("/api/auth/**").permitAll()
>>>>>>> Stashed changes
				.anyRequest().authenticated()
					)
			.sessionManagement(sessionManager -> sessionManager 
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.build();

	}

}
