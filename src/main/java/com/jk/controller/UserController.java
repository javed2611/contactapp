package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.constants.AppConstants;
import com.jk.dto.LoginDTO;
import com.jk.dto.RegisterDTO;
import com.jk.entity.UserEntity;
import com.jk.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String register(Model model) {
		RegisterDTO registerDTO = new RegisterDTO();
		model.addAttribute("registerDTO", registerDTO);
		return AppConstants.INDEX;
	}

	@PostMapping("/save")
	public String handleRegister(RegisterDTO registerDTO, Model model) {
		boolean duplicateEmail = service.duplicateEmail(registerDTO);
		if (duplicateEmail) {
			model.addAttribute("emsg", "User Already Present");
			return AppConstants.INDEX;
		}
		UserEntity user = service.saveUser(registerDTO);
		if (user != null) {
			model.addAttribute("smsg", "Registration Done");
		} else {
			model.addAttribute("emsg", "Unable to register");
		}

		return AppConstants.INDEX;
	}

	@GetMapping("/login")
	public String login(Model model) {
		LoginDTO loginDTO = new LoginDTO();
		model.addAttribute("log", loginDTO);
		return "login";
	}

	@PostMapping("/login")
	public String handleLogin(LoginDTO loginDTO, HttpServletRequest httpServletRequest, Model model) {

		UserEntity entity = service.login(loginDTO);
		if (entity != null) {
			HttpSession session = httpServletRequest.getSession(true);
			session.setAttribute("user", entity);
			model.addAttribute("user", entity);
			return "dashboard";
		} else {
			model.addAttribute("emsg", "Invalid Credentials");
		}
		return "login";

	}
}
