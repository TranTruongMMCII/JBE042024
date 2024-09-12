package com.r2s.java_backend_04.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.r2s.java_backend_04.dto.request.SignUpRequest;
import com.r2s.java_backend_04.dto.request.UserRequestDTO;
import com.r2s.java_backend_04.dto.response.UserResponseDTO;
import com.r2s.java_backend_04.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(source = "deleted", target = "isDeleted")
	@Mapping(target = "cccd", source = "profile.cccd")
	UserResponseDTO toResponse(final User user);
	
	@Mapping(source = "cccd", target = "profile.cccd")
	User toModel(final UserRequestDTO dto);
	
	User toModel(final SignUpRequest request);
}
