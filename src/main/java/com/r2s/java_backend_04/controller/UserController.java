package com.r2s.java_backend_04.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.java_backend_04.dto.request.UserRequestDTO;
import com.r2s.java_backend_04.dto.response.UserResponseDTO;
import com.r2s.java_backend_04.mapper.UserMapper;
import com.r2s.java_backend_04.model.User;
import com.r2s.java_backend_04.response.SuccessResponse;
import com.r2s.java_backend_04.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//@Controller
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

	@Qualifier(value = "UserServiceImpl")
	private final UserService userService;
	
	private final UserMapper userMapper;

//	@GetMapping(path = "/hello")
	@Deprecated
	@RequestMapping(path = "/hello")
	public String hello() {
		return "hello, world";
	}

	/**
	 * Get all users
	 * 
	 * @return
	 */
	@GetMapping(path = "")
//	@PreAuthorize(value = "hasRole('ADMIN')")
	public SuccessResponse<List<UserResponseDTO>> getAll(Pageable pageable,
			@RequestParam(name = "sort", defaultValue = "") String sort) {

		// lay user hien tai dang dang nhap
		var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		System.err.println(currentUser);
		
		PageRequest pageRequest;

		// kiem tra xem co sort hay khong
		String listSort[] = sort.split(",");
		if (listSort.length != 0) {
			List<Order> orders = new ArrayList<>();
			for (int i = 0; i < listSort.length; i += 2) {
				orders.add(new Order(this.buildDirection(listSort[i + 1]), listSort[i]));
			}
			pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
		} else { // khong co sort
			pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
		}

		return SuccessResponse.of(this.userService.getAll(pageRequest)
				.stream()
				.map(this.userMapper::toResponse)
				.toList());
	}

	private Direction buildDirection(final String direction) {
		if (Direction.ASC.toString().equalsIgnoreCase(direction)) {
			return Direction.ASC;
		}

		return Direction.DESC;
	}

	@GetMapping(path = "/{id}")
	public SuccessResponse<User> findByIdPathVariable(@PathVariable(value = "id") int id) {
		User foundUser = this.userService.findById(id);
		return SuccessResponse.of(foundUser);
	}

	@GetMapping(path = "/findById")
	public User findByIdParam(@RequestParam(name = "id", required = false, defaultValue = "1") int id) {
		User foundUser = this.userService.findById(id);
		return foundUser;
	}

	@GetMapping(path = "/findByName")
	public SuccessResponse<List<UserResponseDTO>> findByName(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		return SuccessResponse.of(this.userService.findByName(name)
				.stream()
				.map(UserMapper.INSTANCE::toResponse)
				.toList());
	}

	@PostMapping(path = "")
	public User addUser(@RequestBody @Valid UserRequestDTO dto) {
		User user = UserMapper.INSTANCE.toModel(dto);
		return this.userService.addUser(user);
	}

	@PutMapping(path = "")
	public User updateUser(@RequestBody User newUser) {
		return this.userService.updateUser(newUser);
	}

	@DeleteMapping(path = "/{id}")
	public Boolean deleteUser(@PathVariable(name = "id") int id) {
		return this.userService.deleteUser(id);
	}
	
	@PostMapping(path = "/addClass")
	public Boolean addClassRoom(
			@RequestParam(name = "userId", required = false, defaultValue = "1") int userId,
			@RequestParam(name = "classId", required = false, defaultValue = "1") int classId) {
		return this.userService.addClass(userId, classId);
	}
}
