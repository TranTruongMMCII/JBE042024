package com.r2s.java_backend_04.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.r2s.java_backend_04.constant.SecurityRole;
import com.r2s.java_backend_04.exception.UserNotFoundException;
import com.r2s.java_backend_04.model.ClassRoom;
import com.r2s.java_backend_04.model.User;
import com.r2s.java_backend_04.repository.ClassRoomRepository;
import com.r2s.java_backend_04.repository.RoleRepository;
import com.r2s.java_backend_04.repository.UserRepository;
import com.r2s.java_backend_04.service.UserService;

import lombok.RequiredArgsConstructor;

@Service(value = "UserServiceImpl")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ClassRoomRepository classRoomRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@Override
	public User addUser(User user) {
		user.setDeleted(false);
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User findById(Integer id) {
		return this.userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}

	@Override
	public User updateUser(User newUser) {
		int id = newUser.getId();
		User oldUser = this.userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		if (Objects.isNull(oldUser)) {
			System.err.println("Khong tim thay user co id " + id);
			return null;
		}

		oldUser.setName(newUser.getName());
		oldUser.setAge(newUser.getAge());
		return this.userRepository.save(oldUser);
	}

	@Override
	public Boolean deleteUser(int id) {
		User oldUser = this.userRepository.findById(id).orElse(null);
		if (Objects.isNull(oldUser)) {
			System.err.println("Khong tim thay user co id " + id);
			return null;
		}

		oldUser.setDeleted(true);
		this.userRepository.save(oldUser);
		return true;
	}

	@Override
	public List<User> findByName(String name) {
//		return this.userRepository.findByName(name);
//		return this.userRepository.findByNameContaining(name);
//		return this.userRepository.findByNameContains(name);
		return this.userRepository.findByNameByQuery("%" + name + "%");
	}

	@Override
	public List<User> getAll(Pageable pageable) {
		return this.userRepository.findAll(pageable).stream().toList();
	}

	@Override
	public Boolean addClass(int userId, int classId) {
		
		// check user exists
		User foundUser = this.userRepository.findById(userId)
				.orElseThrow(()->new UserNotFoundException(userId));
		
		// check classId exists
		ClassRoom foundClassRoom = this.classRoomRepository.findById(classId)
				.orElseThrow();
//		System.err.println(foundClassRoom.getUsers());;
		
		// update from user
//		foundUser.setClassRoom(foundClassRoom);
//		this.userRepository.save(foundUser);
		
		/*
		 * update from classroom
		 * 
		 * */
		foundUser.setClassRoom(foundClassRoom);
		foundClassRoom.getUsers().add(foundUser);
		this.classRoomRepository.save(foundClassRoom);
		
		return true;
	}

	@Override
	public Boolean signUp(User user) {
		
		// check exists userName
		this.userRepository.findByUserName(user.getUserName())
		.ifPresent((u) -> {
			throw new RuntimeException("username exists");
		});
		
		user.setDeleted(false);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setRoles(this.roleRepository.findByName(SecurityRole.ROLE_USER));
		this.userRepository.save(user);
		return true;
	}

}
