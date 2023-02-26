package com.fronchak.e_commerce_v2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fronchak.e_commerce_v2.mappers.UserMapper;
import com.fronchak.e_commerce_v2.repositories.RoleRepository;
import com.fronchak.e_commerce_v2.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserMapper mapper;
}
