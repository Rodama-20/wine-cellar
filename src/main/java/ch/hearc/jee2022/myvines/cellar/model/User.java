package ch.hearc.jee2022.myvines.cellar.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
public @Data class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;
	private String password;
	
	private Boolean isAdmin;

	@OneToMany(mappedBy = "user")
	private Set<UserVine> vines;

}
