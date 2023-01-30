package ch.hearc.jee2022.myvines.cellar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserVineTest {
	
	@Test
	public void testValueCellar() {
		List<UserVine> list = new ArrayList<>();
		
		Vine vine1 = new Vine();
		vine1.setPrice(7.50);
		Vine vine2 = new Vine();
		vine2.setPrice(15.0);
		
		UserVine uv1 = new UserVine();
		uv1.setVine(vine1);
		uv1.setAmount(2);
		UserVine uv2 = new UserVine();
		uv2.setVine(vine2);
		uv2.setAmount(1);
		
		list.add(uv1);
		list.add(uv2);
		
		Optional<Double> value = UserVine.cellarValue(list);
		
		Assertions.assertEquals(30.0, value.get());
		
	}
	
	@Test
	public void testEmptyCellar() {
		List<UserVine> list = new ArrayList<>();
		
		Optional<Double> value = UserVine.cellarValue(list);
		
		Assertions.assertFalse(value.isPresent());
	}

}
