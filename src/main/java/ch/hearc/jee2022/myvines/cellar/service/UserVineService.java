package ch.hearc.jee2022.myvines.cellar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.UserVine;
import ch.hearc.jee2022.myvines.cellar.model.Vine;

public interface UserVineService {
	public void addVineToUser(UserVine userVine);

	public void removeVineFromUser(UserVine userVine);

	public List<UserVine> getAllVineFormUser(User user);

	public Optional<UserVine> getUV(User user, Vine vine);

	public Page<UserVine> getAllVineFormUserPageable(User user, Pageable page);
}
