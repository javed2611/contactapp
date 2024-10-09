package com.jk.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.dto.LoginDTO;
import com.jk.dto.RegisterDTO;
import com.jk.entity.UserEntity;
import com.jk.repo.UserRepo;

@Service
public class UserService {
	private UserRepo repo;

	public UserService(UserRepo repo) {
		this.repo = repo;
	}

	public UserEntity saveUser(RegisterDTO registerDTO) {
		ObjectMapper object = new ObjectMapper();
		UserEntity userEntity = object.convertValue(registerDTO, UserEntity.class);
		return repo.save(userEntity);
	}

	public boolean duplicateEmail(RegisterDTO registerDTO) {
		UserEntity email = repo.findByEmail(registerDTO.getEmail());
		return email != null;
	}

	public UserEntity login(LoginDTO loginDTO) {
		return repo.findByEmailAndPwd(loginDTO.getEmail(), loginDTO.getPwd());
	}

}
