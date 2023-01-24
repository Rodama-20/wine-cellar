package ch.hearc.jee2022.myvines.cellar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ch.hearc.jee2022.myvines.cellar.model.Vine;

public interface CellarService {

	public void addVineToCellar(Vine vine);

	public Page<Vine> getAllVinesFromCellar(Pageable page);

	public List<Vine> getAllVines();

	public void deleteVine(Long id);

	public Vine updateVine(Vine vine);

	public Vine getVineById(Long id);

}
