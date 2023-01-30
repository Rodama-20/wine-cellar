package ch.hearc.jee2022.myvines.cellar.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserVineKeyTest {

	@Test
	public void testEqualsNull() {
		UserVineKey uvk = new UserVineKey();
		uvk.setUserId(1L);
		uvk.setVineId(1L);

		Assertions.assertFalse(uvk.equals(null));
	}

	@Test
	public void testEqualsItself() {
		UserVineKey uvk = new UserVineKey();
		uvk.setUserId(1L);
		uvk.setVineId(1L);

		Assertions.assertTrue(uvk.equals(uvk));
	}

	@Test
	public void testEqualsSame() {
		UserVineKey uvk1 = new UserVineKey();
		uvk1.setUserId(1L);
		uvk1.setVineId(1L);

		UserVineKey uvk2 = new UserVineKey();
		uvk2.setUserId(1L);
		uvk2.setVineId(1L);

		Assertions.assertTrue(uvk1.equals(uvk2));
	}

	@Test
	public void testEqualsDifferent() {
		UserVineKey uvk1 = new UserVineKey();
		uvk1.setUserId(1L);
		uvk1.setVineId(1L);

		UserVineKey uvk2 = new UserVineKey();
		uvk2.setUserId(2L);
		uvk2.setVineId(2L);

		Assertions.assertFalse(uvk1.equals(uvk2));
	}

}
