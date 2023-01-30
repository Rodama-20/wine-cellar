package ch.hearc.jee2022.myvines.cellar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.UserVine;
import ch.hearc.jee2022.myvines.cellar.model.UserVineKey;
import ch.hearc.jee2022.myvines.cellar.model.Vine;
import ch.hearc.jee2022.myvines.cellar.repository.UserVineRepository;
import ch.hearc.jee2022.myvines.cellar.service.UserVineService;

@Service
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
		return repository.findByUser(user);
	}

	@Override
	public Optional<UserVine> getUV(User user, Vine vine) {
		UserVineKey uvKey = new UserVineKey();
		uvKey.setUserId(user.getId());
		uvKey.setVineId(vine.getId());
		return repository.findById(uvKey);
	}

	@Override
	public Page<UserVine> getAllVineFormUserPageable(User user, Pageable page) {
		return repository.findByUser(user, page);
	}

}
