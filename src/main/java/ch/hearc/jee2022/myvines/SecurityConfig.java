package ch.hearc.jee2022.myvines;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http//
				.authorizeRequests()//
				.antMatchers("/", "/home")//
				.permitAll()//
				.anyRequest()//
				.authenticated()//
				.and()//
				.formLogin()//
				.permitAll();

		http.logout().logoutSuccessUrl("/admin");

		return http.build();

	}

}
