package com.fronchak.e_commerce_v2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.e_commerce_v2.entities.User;
import com.fronchak.e_commerce_v2.exceptions.UnauthorizedException;
import com.fronchak.e_commerce_v2.repositories.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return repository.findByUsername(username);
		}
		catch(Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}
}
