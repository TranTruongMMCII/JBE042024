package com.r2s.java_backend_04.response;

import java.time.OffsetDateTime;
import java.util.Collection;

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
public class SuccessResponse<T> {

	private final ResponseStatus status = ResponseStatus.SUCCESS;
	
	private T content;
	private int size;
	private OffsetDateTime timestamp;
	
	@SuppressWarnings("rawtypes")
	public static <T> SuccessResponse<T> of(T data){
		return SuccessResponse.<T>builder()
				.content(data)
				.size(data instanceof Collection ? ((Collection) data).size() : 0) 
				.timestamp(OffsetDateTime.now())
				.build();
	}
}
