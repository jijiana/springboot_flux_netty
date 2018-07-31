package com.lin.webflux.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lin.webflux.entity.User;

@Repository
//@Scope("prototype")
@Qualifier("userRepository")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Integer findCountById(Integer id);

}
