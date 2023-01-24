package ch.hearc.jee2022.myvines.cellar.service;

import java.util.List;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.UserVine;

public interface UserVineService {
	public void addVineToUser(UserVine userVine);

	public void removeVineFromUser(UserVine userVine);

	public List<UserVine> getAllVineFormUser(User user);
}
