package com.r2s.java_backend_04.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SumUtilsTest {

//	@BeforeEach
	@BeforeAll
	static void init() {
		// obj.A = "default" -> def1
		System.err.println("Hello, World!");
	}

//	@AfterEach
	@AfterAll
	static void tearDown() {
		System.err.println("End hellow!");
	}

	@Test
	@DisplayName(value = "test sum with happy case")
	void sumTest() {
		// given
		int a = 5;
		int b = 10;

		// when
		int givenSum = SumUtils.sum(a, b);

		// then
		int expectedSum = 15;
		assertEquals(expectedSum, givenSum);
	}

	@ParameterizedTest
	@ValueSource(ints = { 10, 20, 30, 40, 50, 60 })
	@DisplayName(value = "test sum with many numbers")
	void sumTest1(final int a) {
		// given
		int b = 10;

		// when
		int givenSum = SumUtils.sum(a, b);

		// then
		int expectedSum = a + b;
		assertEquals(expectedSum, givenSum);
	}

	@ParameterizedTest
	@CsvSource(value = { "10, 10", "10, 20", "30, 30", "0, 10" })
	@DisplayName(value = "test sum with many numbers + 2")
	void sumTest2(final int a, final int b) {
//		// given
//		int b = 10;

		// when
		int givenSum = SumUtils.sum(a, b);

		// then
		int expectedSum = a + b;
		assertEquals(expectedSum, givenSum);
	}

	@Test
	@DisplayName(value = "test divide with happy case")
	void divideTest() {
		// given
		int a = 50;
		int b = 10;

		// when
		int givenDivide = SumUtils.divide(a, b);

		// then
		int expectedDivide = 5;
		assertEquals(expectedDivide, givenDivide);
	}

	@Test
	@DisplayName(value = "test divide with 0")
	void divideTest1() {
		// given
		int a = 50;
		int b = 0;

		// when
//		int givenDivide = SumUtils.divide(a, b);

		// then
//		int expectedDivide = 5;
		assertThrows(ArithmeticException.class, () -> SumUtils.divide(a, b));
		assertThrows(Exception.class, () -> SumUtils.divide(a, b));
		assertThrowsExactly(ArithmeticException.class, () -> SumUtils.divide(a, b));

		var exception = assertThrows(ArithmeticException.class, () -> SumUtils.divide(a, b));
		assertEquals("khong the chi cho 0", exception.getMessage());
	}
}
