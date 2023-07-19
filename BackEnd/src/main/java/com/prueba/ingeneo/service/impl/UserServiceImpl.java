package com.prueba.ingeneo.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.ingeneo.config.JwtService;
import com.prueba.ingeneo.dto.RegisterRequest;
import com.prueba.ingeneo.dto.RequestDTO;
import com.prueba.ingeneo.dto.ResponseDTO;
import com.prueba.ingeneo.entity.Token;
import com.prueba.ingeneo.entity.TokenType;
import com.prueba.ingeneo.entity.User;
import com.prueba.ingeneo.repository.TokenRepository;
import com.prueba.ingeneo.repository.UserRepository;
import com.prueba.ingeneo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	TokenRepository tokenRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;

	@Override
	public void updateUser(User user) {
		Optional<User> userFound = userRepository.findById(user.getUserId());
		if (userFound.isPresent()) {
			userRepository.save(user);
		}
	}

	public ResponseDTO registerUser(RegisterRequest request) {
		var user = User.builder().userName(request.getUserName()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();
		var savedUser = userRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		saveUserToken(savedUser, jwtToken);
		return ResponseDTO.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
	}

	@Override
	public void delete(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		user.ifPresent(userRepository::delete);
	}

	@Override
	public List<User> getUserList() {
		return userRepository.findAll(Sort.by("userName").ascending());
	}

	@Override
	public Optional<User> findByUserId(Long userId) {
		return userRepository.findById(userId);
	}

	public ResponseDTO authenticateUser(RequestDTO request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return ResponseDTO.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
	}

	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false)
				.build();
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUsername(refreshToken);
		if (userEmail != null) {
			var user = this.userRepository.findByEmail(userEmail).orElseThrow();
			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);
				var authResponse = ResponseDTO.builder().accessToken(accessToken).refreshToken(refreshToken).build();
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}
}
