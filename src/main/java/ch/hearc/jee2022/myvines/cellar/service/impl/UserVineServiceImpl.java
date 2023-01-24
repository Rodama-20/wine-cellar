package ch.hearc.jee2022.myvines.cellar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.UserVine;
import ch.hearc.jee2022.myvines.cellar.repository.UserVineRepository;
import ch.hearc.jee2022.myvines.cellar.service.UserVineService;

public class UserVineServiceImpl implements UserVineService {

	@Autowired
	UserVineRepository repository;

	@Override
	public void addVineToUser(UserVine userVine) {
		repository.save(userVine);

	}

	@Override
	public void removeVineFromUser(UserVine userVine) {
		repository.delete(userVine);

	}

	@Override
	public List<UserVine> getAllVineFormUser(User user) {
		return repository.findVineByUser(user);
	}

}
