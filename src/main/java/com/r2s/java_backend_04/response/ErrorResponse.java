package com.r2s.java_backend_04.response;

import java.time.OffsetDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class ErrorResponse {

	private final ResponseStatus status = ResponseStatus.FAILURE;
	
	private String message;
	private String code;
	private Map<String, Object> errorDetails;
	private OffsetDateTime timestamp;
	
	public static ErrorResponse of(final String code, final String message) {
		return ErrorResponse.builder()
				.code(code)
				.message(message)
				.timestamp(OffsetDateTime.now())
				.build();
	}
	
	public static ErrorResponse of(final String code, final String message, final Map<String, Object> details) {
		return ErrorResponse.builder()
				.code(code)
				.message(message)
				.errorDetails(details)
				.timestamp(OffsetDateTime.now())
				.build();
	}
}
