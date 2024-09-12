package com.r2s.java_backend_04.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.java_backend_04.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	List<Role> findByName(String name);
}
