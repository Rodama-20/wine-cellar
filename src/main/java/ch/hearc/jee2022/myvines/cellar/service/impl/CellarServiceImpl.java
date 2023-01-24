package ch.hearc.jee2022.myvines.cellar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.hearc.jee2022.myvines.cellar.model.Vine;
import ch.hearc.jee2022.myvines.cellar.repository.VineRepository;
import ch.hearc.jee2022.myvines.cellar.service.CellarService;

@Service
public class CellarServiceImpl implements CellarService {

	@Autowired
	VineRepository vineRepository;

	@Override
	public void addVineToCellar(Vine vine) {
		vineRepository.save(vine);

	}

	@Override
	public Page<Vine> getAllVinesFromCellar(Pageable page) {
		return vineRepository.findAll(page);
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

	@Override
	public List<Vine> getAllVines() {
		List<Vine> list = new ArrayList<>();
		vineRepository.findAll().forEach(list::add);
		return list;
	}

}
