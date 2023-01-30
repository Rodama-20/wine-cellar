package ch.hearc.jee2022.myvines.cellar.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.UserDto;

public interface UserService extends UserDetailsService {
	public User register(UserDto newUser) throws Exception;
	
	public User save(User user);

}
