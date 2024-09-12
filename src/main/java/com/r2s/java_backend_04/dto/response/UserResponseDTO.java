package com.r2s.java_backend_04.dto.response;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.java_backend_04.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(name = "UserResponseDTO", description = "DTO of response when creating user"
//, title = "DTO Request User"
)
public class UserResponseDTO {

	private Integer id;
	private String name;
	private Integer age;
	private Boolean isDeleted;
	private String cccd;
	
	@JsonFormat(timezone = "UTC", pattern = "dd/MM/yyyy hh:mm:ss")
	private Date timestamp = new Date();

	public UserResponseDTO(final User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.age = user.getAge();
		this.isDeleted = user.getDeleted();

		if (Objects.nonNull(user.getProfile())) {
			this.cccd = user.getProfile().getCccd();
		}
	}
}
