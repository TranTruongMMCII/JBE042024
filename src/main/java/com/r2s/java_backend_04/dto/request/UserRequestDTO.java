package com.r2s.java_backend_04.dto.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.r2s.java_backend_04.dto.response.UserResponseDTO;
import com.r2s.java_backend_04.model.Profile;
import com.r2s.java_backend_04.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "UserRequestDTO", description = "DTO of request when creating user"
//, title = "DTO Request User"
)
public class UserRequestDTO {

	@Schema(description = "ten cua user ne", title = "name")
	@NotBlank(message = "ten user lam on khong duoc de trong, cho nay test branch test")
	private String name;

	@Min(value = 18L, message = "tuoi tu 18 tro len")
	@Max(value = 100L, message = "100 tuoi tro xuong")
	private Integer age;
	private Boolean deleted;
	
	@Schema(hidden = true)
	private String cccd;
}
