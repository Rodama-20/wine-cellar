package ch.hearc.jee2022.myvines.vines.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.jee2022.myvines.vines.model.Vine;

public interface VineRepository extends CrudRepository<Vine, Long> {

}
