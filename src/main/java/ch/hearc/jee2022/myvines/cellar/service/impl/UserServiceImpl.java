package ch.hearc.jee2022.myvines.cellar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.UserDto;
import ch.hearc.jee2022.myvines.cellar.repository.UserRepository;
import ch.hearc.jee2022.myvines.cellar.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User register(UserDto newUser) throws Exception {
		if (!repository.findAllByUsername(newUser.getUsername()).isEmpty()) {
			throw new Exception("Username not availible");
		}
		User user = new User();
		user.setUsername(newUser.getUsername());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setAdmin(null);
		return repository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUserName(username);
	}

	@Override
	public User save(User user) {
		if (repository.findAllByUsername(user.getUsername()).isEmpty()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return repository.save(user);
		}
		return null;
	}

}
