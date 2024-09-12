package com.r2s.java_backend_04.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.r2s.java_backend_04.exception.UserNotFoundException;
import com.r2s.java_backend_04.model.User;
import com.r2s.java_backend_04.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	private static final Integer ID = 9999999;
	private static final String USER_NAME1 = "UserTest01";
	private static final String USER_NAME2 = "UserTest02";
	private static final User USER1 = User.builder().age(25).name(USER_NAME1).userName(USER_NAME1).build();
	private static final User USER2 = User.builder().age(52).name(USER_NAME2).userName(USER_NAME2).build();

	@Test
	void testFindById() {
		// given 
		when(this.userRepository.findById(eq(ID))).thenReturn(Optional.of(USER1));
		
		// when
		final var givenUser = this.userService.findById(ID);
		
		// then
		assertNotNull(givenUser);
		assertEquals(USER_NAME1, givenUser.getName());
		assertEquals(USER_NAME1, givenUser.getUserName());
		assertEquals(25, givenUser.getAge());
	}

	@Test
	void testFindByIdNotFound() {
		// given 
		when(this.userRepository.findById(eq(ID))).thenReturn(Optional.empty());
		
		// when
		final Executable execute = ()-> this.userService.findById(ID);
		
		// then
		assertThrows(UserNotFoundException.class, execute);
	}
}
