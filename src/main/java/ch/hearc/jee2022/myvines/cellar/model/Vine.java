package ch.hearc.jee2022.myvines.cellar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Vine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private Integer year;
	private String country;
	private Double rating;
	private Integer quantity;
	private String image;
	private String comment;

	private Integer userId;
}
