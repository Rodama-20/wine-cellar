package ch.hearc.jee2022.myvines.cellar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ch.hearc.jee2022.myvines.cellar.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	
	public List<User> findAllByUsername(String username);
	
	@Query("SELECT u From User u WHERE u.username = ?1")
	public User findByUserName(String username);
}
