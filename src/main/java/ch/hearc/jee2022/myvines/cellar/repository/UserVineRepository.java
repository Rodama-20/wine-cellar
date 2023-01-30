package ch.hearc.jee2022.myvines.cellar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ch.hearc.jee2022.myvines.cellar.model.UserVine;
import ch.hearc.jee2022.myvines.cellar.model.UserVineKey;

public interface UserVineRepository
		extends CrudRepository<UserVine, UserVineKey>, PagingAndSortingRepository<UserVine, UserVineKey> {

	public List<UserVine> findAllByUserId(Long userId);

	public Page<UserVine> findAllByUserId(Long userId, Pageable page);

}
