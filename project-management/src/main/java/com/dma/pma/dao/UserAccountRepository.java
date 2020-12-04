package com.dma.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dma.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

}
