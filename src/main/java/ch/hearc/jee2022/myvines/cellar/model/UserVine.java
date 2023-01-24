package ch.hearc.jee2022.myvines.cellar.model;

import java.util.Optional;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.data.repository.CrudRepository;

@Entity
public class UserVine {

	@EmbeddedId
	private UserVineKey id;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("vineId")
	@JoinColumn(name = "vine_id")
	private Vine vine;

	private Integer amount;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	

}
