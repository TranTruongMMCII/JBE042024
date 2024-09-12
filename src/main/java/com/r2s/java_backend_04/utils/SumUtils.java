package com.r2s.java_backend_04.utils;

public class SumUtils {
	public static int sum(final int a, final int b) {
		return a + b;
	}

	public static int divide(final int a, final int b) {
		if (b == 0) {
			throw new ArithmeticException("khong the chi cho 0");
		}

		return a / b;
	}
}
