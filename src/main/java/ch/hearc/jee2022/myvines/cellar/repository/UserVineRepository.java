package ch.hearc.jee2022.myvines.cellar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.UserVine;
import ch.hearc.jee2022.myvines.cellar.model.UserVineKey;

public interface UserVineRepository extends CrudRepository<UserVine, UserVineKey> {

	public List<UserVine> findVineByUser(User user);

}
