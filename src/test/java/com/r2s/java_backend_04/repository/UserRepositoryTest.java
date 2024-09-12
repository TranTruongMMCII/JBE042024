package com.r2s.java_backend_04.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.r2s.java_backend_04.model.User;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	private static final String USER_NAME1 = "UserTest01";
	private static final String USER_NAME2 = "UserTest02";
	private static final User USER1 = User.builder().age(25).name(USER_NAME1).userName(USER_NAME1).build();
	private static final User USER2 = User.builder().age(52).name(USER_NAME2).userName(USER_NAME2).build();

	@BeforeAll
	void init() {
		this.repository.saveAll(List.of(USER1, USER2));
	}

	@AfterAll
	void tearDown() {
		final var user1 = this.repository.findByName(USER_NAME1);
		final var user2 = this.repository.findByName(USER_NAME2);
		user1.addAll(user2);
		this.repository.deleteAll(user1);
	}

	@Test
	void testFindByUserName() {
		// given

		// when
		final var optionalUser = this.repository.findByUserName(USER_NAME1);

		// then
		assertTrue(optionalUser.isPresent());
		final var foundUser = optionalUser.get();
		assertEquals(USER_NAME1, foundUser.getName());
		assertEquals(25, foundUser.getAge());
	}

	@Test
	void testFindByUserNameNotFound() {
		// given

		// when
		final var optionalUser = this.repository.findByUserName(USER_NAME1 + "1");

		// then
		assertTrue(optionalUser.isEmpty());
	}
}
