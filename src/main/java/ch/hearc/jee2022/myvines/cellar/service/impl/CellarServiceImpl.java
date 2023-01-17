package ch.hearc.jee2022.myvines.cellar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.jee2022.myvines.cellar.model.Vine;
import ch.hearc.jee2022.myvines.cellar.repository.VineRepository;
import ch.hearc.jee2022.myvines.cellar.service.CellarService;

@Service
public class CellarServiceImpl implements CellarService{

	@Autowired
	VineRepository vineRepository;
	
	
	@Override
	public void addVineToCellar(Vine vine) {
		vineRepository.save(vine);
		
	}

	@Override
	public List<Vine> getAllVinesFromCellar() {
		List<Vine> resultList = new ArrayList<>();
		vineRepository.findAll().forEach(resultList::add);
		return resultList;
	}

	@Override
	public void deleteVine(Long id) {
		vineRepository.deleteById(id);
		
	}

	@Override
	public Vine updateVine(Vine vine) {
		return vineRepository.save(vine);
	}

	@Override
	public Vine getVineById(Long id) {
		return vineRepository.findById(id).get();
	}

}
