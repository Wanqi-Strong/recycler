package com.recycler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.recycler.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private UserDetailsService UserServiceImpl;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .cors() // enable CORS
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        .and()
//        .authorizeHttpRequests((authorize) -> authorize
//        	.requestMatchers("**/user/login").permitAll()
//        	.requestMatchers("**//user/add").permitAll()
//        	.requestMatchers("/user/**").hasRole("USER")       
//			.requestMatchers("/admin/**").hasRole("ADMIN")
//            .anyRequest().authenticated()
//            );
        return http.build();
        }
	
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder authentication) throws Exception {
//	    authentication.userDetailsService(userDetailsService());
//	    //authentication.authenticationProvider(authenticationProvider());
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//		//daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//	}
	@Bean
	public UserDetailsService userDetailsService() {
		return UserServiceImpl;
	}
	    
	 // CORS configuration
	 @Bean
	 CorsConfigurationSource corsConfigurationSource() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	        return source;
	    }
}
