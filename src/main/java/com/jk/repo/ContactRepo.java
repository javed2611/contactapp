package com.jk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jk.entity.ContactEntity;
import java.util.List;


@Repository
public interface ContactRepo extends JpaRepository<ContactEntity, Integer> {
	public List<ContactEntity> findByUserId(Integer userId);
}
