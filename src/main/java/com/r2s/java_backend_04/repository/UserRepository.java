package com.r2s.java_backend_04.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.r2s.java_backend_04.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByName(String name);
	
	List<User> findByNameContaining(String name);
	
	List<User> findByNameContains(String name);
	
	@Query(nativeQuery = true, value = "select * FROM `jbe042024`.`user` where name like ?1")
	List<User> findByNameByQuery(String name);
	
	Optional<User> findByUserName(String name);
}
