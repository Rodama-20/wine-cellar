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
		return repository.findAllByUserId(user.getId());
	}

	/**
	 * Build the composite key and use it to search the many to many relation
	 */
	@Override
	public Optional<UserVine> getUV(User user, Vine vine) {
		UserVineKey uvKey = new UserVineKey();
		uvKey.setUserId(user.getId());
		uvKey.setVineId(vine.getId());
		return repository.findById(uvKey);
	}

	@Override
	public Page<UserVine> getAllVineFormUserPageable(User user, Pageable page) {
		return repository.findAllByUserId(user.getId(), page);
	}

	/**
	 * Build the composite key and use it to edit the amount of vine the user has in
	 * its cellar
	 */
	@Override
	public void editAmount(User user, Long vineId, Integer amount) {
		UserVineKey uvKey = new UserVineKey();
		uvKey.setUserId(user.getId());
		uvKey.setVineId(vineId);
		UserVine uv = repository.findById(uvKey).get();
		uv.setAmount(amount);
		repository.deleteById(uvKey);
		repository.save(uv);
	}

}
