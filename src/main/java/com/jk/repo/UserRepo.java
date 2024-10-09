package com.jk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jk.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByEmailAndPwd(String email, String password);

	public UserEntity findByEmail(String email);
}
