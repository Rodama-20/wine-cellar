package ch.hearc.jee2022.myvines.cellar.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class Vine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private Integer year;
	private String country;
	private Double rating;
	private String image;
	private String comment;

	@OneToMany(mappedBy = "vine")
	private Set<UserVine> owners;
}
