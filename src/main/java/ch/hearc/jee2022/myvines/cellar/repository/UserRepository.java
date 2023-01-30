package ch.hearc.jee2022.myvines.cellar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.jee2022.myvines.cellar.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findAllByUsername(String username);

	public User findByUsername(String username);
}
