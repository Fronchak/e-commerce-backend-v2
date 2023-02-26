package com.fronchak.e_commerce_v2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.e_commerce_v2.dtos.user.UserInsertDTO;
import com.fronchak.e_commerce_v2.dtos.user.UserOutputDTO;
import com.fronchak.e_commerce_v2.dtos.user.UserUpdateDTO;
import com.fronchak.e_commerce_v2.entities.User;
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public UserOutputDTO save(UserInsertDTO inputDTO) {
		User entity = new User();
		entity.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
		entity.addRole(roleRepository.getReferenceById(1L));
		entity = userRepository.save(entity);
		return mapper.convertUserToUserOutputDTO(entity);
	}
	
	public UserOutputDTO update(UserUpdateDTO inputDTO) {
		
		return null;
	}
	
	public List<UserOutputDTO> findAll() {
		
		return null;
	}
}
