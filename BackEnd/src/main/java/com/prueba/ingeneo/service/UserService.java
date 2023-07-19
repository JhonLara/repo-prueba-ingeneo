package com.prueba.ingeneo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.prueba.ingeneo.dto.RegisterRequest;
import com.prueba.ingeneo.dto.RequestDTO;
import com.prueba.ingeneo.dto.ResponseDTO;
import com.prueba.ingeneo.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

	void updateUser(User user);

	void delete(Long userId);

	List<User> getUserList();

	Optional<User> findByUserId(Long userId);

	ResponseDTO authenticateUser(RequestDTO request);

	void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

	ResponseDTO registerUser(RegisterRequest request);
}
