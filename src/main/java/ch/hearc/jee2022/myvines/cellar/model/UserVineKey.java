package ch.hearc.jee2022.myvines.cellar.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class UserVineKey implements Serializable {

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "vine_id")
	private Long vineId;

	@Override
	public int hashCode() {
		return Objects.hash(userId, vineId);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != UserVineKey.class) {
			return false;
		}
		UserVineKey other = (UserVineKey) obj;
		if (other.userId == this.userId && other.vineId == this.vineId) {
			return true;
		}
		return false;

	}

}
