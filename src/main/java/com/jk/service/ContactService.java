package com.jk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.dto.ContactDTO;
import com.jk.entity.ContactEntity;
import com.jk.repo.ContactRepo;

@Service
public class ContactService {
	private ContactRepo contactRepo;

	public ContactService(ContactRepo contactRepo) {
		this.contactRepo = contactRepo;
	}

	public boolean addContact(ContactDTO contactDTO) {
		ObjectMapper mapper = new ObjectMapper();
		ContactEntity contact = mapper.convertValue(contactDTO, ContactEntity.class);
		ContactEntity save = contactRepo.save(contact);
		return save.getCid() != null;
	}

	public List<ContactEntity> getAllContacts(Integer userId) {
		return contactRepo.findByUserId(userId);
	}
}
