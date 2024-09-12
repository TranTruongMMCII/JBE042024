package com.r2s.java_backend_04.controller;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.java_backend_04.dto.request.SignInRequest;
import com.r2s.java_backend_04.dto.request.SignUpRequest;
import com.r2s.java_backend_04.dto.response.SignInResponse;
import com.r2s.java_backend_04.mapper.UserMapper;
import com.r2s.java_backend_04.model.User;
import com.r2s.java_backend_04.response.SuccessResponse;
import com.r2s.java_backend_04.security.CustomUserDetails;
import com.r2s.java_backend_04.service.UserService;
import com.r2s.java_backend_04.utils.JwtUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	private final UserMapper mapper;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@PostMapping
	public SuccessResponse<Boolean> signUp(@RequestBody SignUpRequest request) {
		User user = this.mapper.toModel(request);
		log.info("{}", user.getUserName());
		return SuccessResponse.of(this.userService.signUp(user));
	}
	
	@PostMapping(path = "/signIn")
	public SuccessResponse<SignInResponse> signIn(@RequestBody SignInRequest request) {
		Authentication authentication = 
				this.authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        // Retrieve user details from the authenticated token
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Generate JWT token
        String accessToken = jwtUtils.generateToken(userDetails);
        Date expriedDate = jwtUtils.extractExpiration(accessToken);

        return SuccessResponse.of(SignInResponse.builder()
        		.token(accessToken)
        		.expiredDate(expriedDate)
        		.build());
	}
}
