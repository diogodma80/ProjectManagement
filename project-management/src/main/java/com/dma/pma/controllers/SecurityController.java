package com.dma.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dma.pma.dao.UserAccountRepository;
import com.dma.pma.entities.UserAccount;
import com.dma.pma.services.UserAccountService;

@Controller
public class SecurityController {
	
	@Autowired
	UserAccountService userAccountService;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		
		model.addAttribute("userAccount", userAccount);
		
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount userAccount) {
		// encodes and overwrites the password for security
		userAccount.setPassword(bCryptEncoder.encode(userAccount.getPassword()));
		userAccountService.save(userAccount);
		return "redirect:/";
	}
}
