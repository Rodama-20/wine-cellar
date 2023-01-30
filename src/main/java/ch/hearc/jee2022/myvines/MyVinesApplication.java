package ch.hearc.jee2022.myvines;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.service.impl.UserServiceImpl;

@SpringBootApplication
public class MyVinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyVinesApplication.class, args);
	}

	@Bean
	public CommandLineRunner InitUser(UserServiceImpl serviceImpl) {
		return args -> {
			try {
				User admin = new User();
				admin.setIsAdmin(true);
				admin.setUsername("admin");
				admin.setPassword("admin");
				serviceImpl.register(admin);

				User user = new User();
				user.setIsAdmin(false);
				user.setUsername("user");
				user.setPassword("user");
				serviceImpl.register(user);
			} catch (Exception e) {

			}
		};
	}

}
