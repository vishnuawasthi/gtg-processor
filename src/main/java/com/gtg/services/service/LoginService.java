package com.gtg.services.service;

import org.springframework.data.domain.Page;

import com.gtg.lib.dto.LoginRequestDTO;
import com.gtg.lib.dto.LoginResponseDTO;
import com.gtg.lib.dto.UserDTO;
import com.gtg.services.criteria.UserSearchCriteria;
import com.gtg.services.exception.InvalidUserException;

/**
 * 
 * @author Vishnu Awasthi
 *
 */
public interface LoginService {

	UserDTO getById(Long id);

	UserDTO getByUsername(String username);

	Page<UserDTO> getAll(UserSearchCriteria criteria);

	LoginResponseDTO login(LoginRequestDTO dto) throws InvalidUserException;

	Long save(UserDTO userDTO) throws InvalidUserException;

	boolean logout(String username) throws InvalidUserException;

	boolean forgotPassword(UserDTO userDTO) throws InvalidUserException;

	boolean forgotPasswordAsync(UserDTO userDTO) throws InvalidUserException;

}
