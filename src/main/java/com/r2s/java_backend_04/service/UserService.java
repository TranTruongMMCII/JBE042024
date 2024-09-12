package com.r2s.java_backend_04.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.r2s.java_backend_04.model.User;

public interface UserService {

	User addUser(User user);

	List<User> getAll();
	
	List<User> getAll(Pageable pageable);

	User findById(Integer id);

	User updateUser(User newUser);

	Boolean deleteUser(int id);

	List<User> findByName(String name);
	
	Boolean addClass(final int userId, final int classId);
	
	Boolean signUp(User user);
}
