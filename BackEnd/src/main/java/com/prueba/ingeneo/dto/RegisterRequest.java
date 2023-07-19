package com.prueba.ingeneo.dto;

import java.io.Serializable;

import com.prueba.ingeneo.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userName;
	private String email;
	private String password;
	private Role role;
}
