package ch.hearc.jee2022.myvines.cellar.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ch.hearc.jee2022.myvines.cellar.model.User;

public interface UserService extends UserDetailsService{
	public User register(User newUser) throws Exception;
	
	
}
