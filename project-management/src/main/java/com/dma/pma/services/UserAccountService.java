package com.dma.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dma.pma.dao.UserAccountRepository;
import com.dma.pma.entities.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository userAccountRepository;

	public void save(UserAccount userAccount) {
		userAccountRepository.save(userAccount);
		
	}

}
