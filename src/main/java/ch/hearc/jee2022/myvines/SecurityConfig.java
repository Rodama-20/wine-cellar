package ch.hearc.jee2022.myvines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder encoder;

	/**
	 * Define the rule to access to the different part of the website. Homepage,
	 * loginform and register form are publicly accessible.
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http//
				.authorizeRequests()//
				.antMatchers("/", "/home", "/register")//
				.permitAll()//
				.anyRequest()//
				.authenticated()//
				.and()//
				.formLogin()//
				.permitAll();

		http.logout().logoutSuccessUrl("/home");

		return http.build();

	}

	/**
	 * Redefine the authentication provider for using our own User model and
	 * password hashing method
	 * 
	 * @return
	 */
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder);
		return authProvider;
	}

	/**
	 * Build the Authentication manager based on our authentication provider
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authProvider()).build();
	}

}
