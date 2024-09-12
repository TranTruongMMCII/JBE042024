package com.r2s.java_backend_04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.java_backend_04.model.Profile;
import com.r2s.java_backend_04.repository.ProfileRepository;
import com.r2s.java_backend_04.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/profiles")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileRepository profileRepository;

	@GetMapping(path = "")
	public SuccessResponse<Profile> getById(@RequestParam(name = "id") Integer id) {
		return SuccessResponse.of(this.profileRepository.findById(id).orElse(null));
	}
}
