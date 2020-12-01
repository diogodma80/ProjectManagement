package com.dma.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.dma.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
