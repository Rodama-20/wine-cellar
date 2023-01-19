package ch.hearc.jee2022.myvines.cellar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ch.hearc.jee2022.myvines.cellar.model.Vine;

public interface VineRepository extends CrudRepository<Vine, Long>, PagingAndSortingRepository<Vine, Long> {

	@Query("SELECT v FROM Vine v WHERE v.user.id = ?1")
	public Page<Vine> findVineByUser(Long userId, Pageable pageable);
	
	@Query("SELECT v FROM Vine WHERE TRUE")
	public Page<Vine> findAllVines(Pageable pageable);
}
