package ch.hearc.jee2022.myvines.cellar.service;

import java.util.List;

import ch.hearc.jee2022.myvines.cellar.model.Vine;

public interface CellarService {

	public void addVineToCellar(Vine vine);
	
	public List<Vine> getAllVinesFromCellar();
	
	public void deleteVine(Long id);
	
	public Vine updateVine(Vine vine);
	
	public Vine getVineById(Long id);
}
