package com.dma.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dma.pma.entities.UserAccount;
//Rest controller will serve from the 'apiemployees' endpoint while 'employees' will continue to serve the page
@RepositoryRestResource(path = "apiuseraccounts", collectionResourceRel = "apiuseraccounts")
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

}
