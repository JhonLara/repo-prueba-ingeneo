package com.prueba.ingeneo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prueba.ingeneo.dto.RegisterRequest;
import com.prueba.ingeneo.dto.RequestDTO;
import com.prueba.ingeneo.dto.ResponseDTO;
import com.prueba.ingeneo.entity.User;
import com.prueba.ingeneo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/v1/auth/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public List<User> getUserList() {
		return userService.getUserList();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable long userId) {
		Optional<User> userFound = userService.findByUserId(userId);
		return userFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody RegisterRequest request)
			throws JsonProcessingException {
		return ResponseEntity.ok(userService.registerUser(request));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<ResponseDTO> authenticate(@RequestBody RequestDTO request) throws JsonProcessingException {
		return ResponseEntity.ok(userService.authenticateUser(request));
	}

	@PostMapping("/refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		userService.refreshToken(request, response);
	}

	@PutMapping()
	public void update(@RequestBody User user) {
		Optional<User> userFound = userService.findByUserId(user.getUserId());
		if (userFound.isPresent()) {
			userService.updateUser(user);
		} else {
			ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.delete(userId);
	}
}
