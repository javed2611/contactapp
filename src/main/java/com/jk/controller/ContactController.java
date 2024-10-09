package com.jk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jk.dto.ContactDTO;
import com.jk.entity.ContactEntity;
import com.jk.entity.UserEntity;
import com.jk.service.ContactService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContactController {

	private ContactService contactService;

	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("contact", new ContactDTO());
		return "addcontact";
	}

	@PostMapping("/contact")
	public String addContact(ContactDTO contactDTO, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		UserEntity u = (UserEntity) session.getAttribute("user");
		contactDTO.setUserId(u.getId());
		boolean contact = contactService.addContact(contactDTO);
		if (contact) {
			model.addAttribute("smsg", "Contact Added");
		} else {
			model.addAttribute("emsg", "Unable to add Contact");
		}
		return "addcontact";
	}

	@GetMapping("/viewcontact")
	public String getContacts(Integer userId, Model model) {
		List<ContactEntity> list = contactService.getAllContacts(userId);
		model.addAttribute("contacts", list);
		return "viewcontact";
	}

}
