package ch.hearc.jee2022.myvines.cellar.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.jee2022.myvines.cellar.model.Vine;

public interface VineRepository extends CrudRepository<Vine, Long> {

}
