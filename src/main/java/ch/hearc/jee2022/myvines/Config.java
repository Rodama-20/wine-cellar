package ch.hearc.jee2022.myvines;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ch.hearc.jee2022.myvines.cellar.service.impl.UserServiceImpl;

@Configuration
public class Config {
	
	/**
	 * Define the password encoder for the application
	 * @return
	 */
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
