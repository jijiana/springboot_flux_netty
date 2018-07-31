package com.lin.webflux.service;

import java.util.Optional;

import com.lin.webflux.entity.User;

public interface IUserService {

	public void register();
 
	public Long checkTransactionalFunc(User newCount);

	Optional<User> findById(Long id);
	
	public void saveOnce(User user);
	
	public Long checkTransactional(Long id) throws Exception;

}
