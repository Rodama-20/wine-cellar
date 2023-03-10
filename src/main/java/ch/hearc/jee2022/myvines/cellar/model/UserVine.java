package ch.hearc.jee2022.myvines.cellar.model;

import java.util.List;
import java.util.Optional;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class UserVine {

	@EmbeddedId
	private UserVineKey id = new UserVineKey();

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("vineId")
	@JoinColumn(name = "vine_id")
	private Vine vine;

	private Integer amount;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vine getVine() {
		return vine;
	}

	public void setVine(Vine vine) {
		this.vine = vine;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public static Optional<Double> cellarValue(List<UserVine> list) {
		return list.stream().map(uv -> uv.amount * uv.vine.getPrice()).reduce(Double::sum);
	}

}
