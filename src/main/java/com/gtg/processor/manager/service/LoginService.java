package com.gtg.processor.manager.service;

import static com.gtg.core.criteria.SortingAndPaginationUtility.createPageRequest;
import static com.gtg.core.criteria.SortingAndPaginationUtility.orderBy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gtg.core.entity.User;
import com.gtg.core.entity.UserRoles;
import com.gtg.core.repository.UserRepository;
import com.gtg.core.repository.UserRolesRepository;
import com.gtg.core.utils.CommonUtility;
import com.gtg.email.service.EmailSenderService;
import com.gtg.processor.constants.Constants;
import com.gtg.processor.criteria.UserSearchCriteria;
import com.gtg.processor.exception.InvalidUserException;
import com.gtg.processor.manager.dto.LoginRequestDTO;
import com.gtg.processor.manager.dto.LoginResponseDTO;
import com.gtg.processor.manager.dto.UserDTO;
import com.gtg.processor.utils.DateTimeUtil;
import com.gtg.processor.utils.GTGUtils;

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

	@Service("loginService")
	public class Impl implements LoginService {

		public static final Logger log = Logger.getLogger(Impl.class);

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private UserRolesRepository userRolesRepository;

		@Autowired
		private EmailSenderService emailSenderService;

		@Override
		public UserDTO getById(Long id) {
			User entity = userRepository.findOne(id);
			return entityToDTO(entity, false);
		}

		@Override
		public UserDTO getByUsername(String username) {
			User entity = userRepository.findUserByUsername(username);
			return entityToDTO(entity, true);
		}

		@Override
		public Page<UserDTO> getAll(UserSearchCriteria criteria) {
			log.info("getAll() - start");
			List<UserDTO> userdtos = new ArrayList<UserDTO>();
			Page<User> page = userRepository.findAll(createPageRequest(criteria.getPageable().getPageNumber(),
					criteria.getPageable().getPageSize(), orderBy(criteria.getSortColumn(), criteria.getSortOrder())));
			List<User> dbContents = page.getContent();
			for (User user : CommonUtility.emptyIfNull(dbContents)) {
				userdtos.add(entityToDTO(user, false));
			}
			Page<UserDTO> pageResponse = new PageImpl<UserDTO>(userdtos, criteria.getPageable(),
					page.getTotalElements());
			log.info("getAll() - end");
			return pageResponse;
		}

		@Override
		public LoginResponseDTO login(LoginRequestDTO dto) throws InvalidUserException {
			log.info("login() - start");
			LoginResponseDTO responseDTO = new LoginResponseDTO();
			User user = userRepository.findUserByUsername(dto.getUsername());
			try {
				if (null != user && validateUser(user, dto)) {

					String apiKey = UUID.randomUUID().toString();
					Date apikeyExpireTime = DateTimeUtil.getApiKeyExpiredTime();

					if (!(user.getApikeyExpireTime() != null && user.getApikeyExpireTime().after(new Date()))) {
						user.setApiKey(apiKey);
						user.setApikeyExpireTime(apikeyExpireTime);
						userRepository.save(user);
					} else if (user.getApikeyExpireTime() == null) {
						user.setApiKey(apiKey);
						user.setApikeyExpireTime(apikeyExpireTime);
						userRepository.save(user);

					}
					responseDTO.setApiKey(user.getApiKey());
					responseDTO.setApikeyExpireTime(user.getApikeyExpireTime());
					responseDTO.setUsername(user.getUsername());
					responseDTO.setApiKey(user.getApiKey());
					responseDTO.setStatus(Constants.STATUS_OK);
					responseDTO.setStatusCode(200);
					log.info("login() - end");
					return responseDTO;
				} else {
					throw new InvalidUserException("Invalid Username or Password");
				}
			} catch (InvalidUserException e) {
				throw new InvalidUserException(e.getMessage());
			}
		}

		@Override
		public boolean logout(String username) throws InvalidUserException {
			log.info("logout() - start");
			User user = userRepository.findUserByUsername(username);
			if (user != null) {
				user.setApiKey(null);
				user.setApikeyExpireTime(null);
				userRepository.save(user);
				log.info("logout() - end");
				return true;
			} else {
				throw new InvalidUserException("User not  found.");
			}
		}

		public boolean validateUser(User user, LoginRequestDTO dto) {
			if (GTGUtils.decode(user.getPassword()).equals(dto.getPassword())
					&& user.getUsername().equals(dto.getUsername())) {
				return true;
			}
			return false;
		}

		@Override
		public Long save(UserDTO userDTO) throws InvalidUserException {
			log.info("save() - start");
			User duplicateEntity = userRepository.findUserByUsername(userDTO.getUsername());

			if (null != duplicateEntity) {
				throw new InvalidUserException("User already exist with given username");
			}

			User entity = dtoToEntity(userDTO);

			UserRoles userRoles = userRolesRepository.findOne(userDTO.getRoleId());
			entity.setUserRoles(userRoles);
			User user = userRepository.save(entity);

			if (user != null) {
				log.info("save() - end");
				return user.getId();
			}
			log.info("save() - end : Error");
			return null;
		}

		private UserDTO entityToDTO(User entity, boolean isLogin) {
			UserDTO dto = null;
			if (null != entity) {
				dto = new UserDTO();
				dto.setId(entity.getId());
				dto.setUsername(entity.getUsername());
				if (isLogin) {
					dto.setPassword(GTGUtils.decode(entity.getPassword()));
				}
				dto.setApiKey(entity.getApiKey());
				dto.setFirstName(entity.getFirstName());
				dto.setLastName(entity.getLastName());
				dto.setEmail(entity.getEmail());
				dto.setRoleId(StringUtils.isEmpty(entity.getUserRoles()) ? null : entity.getUserRoles().getId());
				dto.setRoleName(StringUtils.isEmpty(entity.getUserRoles()) ? "" : entity.getUserRoles().getRoleName());
				dto.setUpdated(entity.getUpdated());
				dto.setCreated(entity.getCreated());
				dto.setUpdatedBy(entity.getUpdatedBy());
				dto.setCreatedBy(entity.getCreatedBy());
				dto.setApikeyExpireTime(entity.getApikeyExpireTime());
				return dto;
			}

			return null;
		}

		private User dtoToEntity(UserDTO dto) {
			User entity = null;
			if (null != dto) {
				entity = new User();

				entity.setId(dto.getId());
				entity.setUsername(dto.getUsername());
				entity.setPassword(GTGUtils.encode(dto.getPassword()));
				// entity.setApiKey(dto.getApiKey());
				entity.setFirstName(dto.getFirstName());
				entity.setLastName(dto.getLastName());
				entity.setEmail(dto.getEmail());

				// entity.setRoles(entity.getUserRoles().getId());
				// entity.setRoleName(entity.getUserRoles().getRoleName());
				// entity.setUpdated(dto.getUpdated());
				// entity.setCreated(entity.getCreated());
				// entity.setUpdatedBy(entity.getUpdatedBy());
				// entity.setCreatedBy(entity.getCreatedBy());
				// entity.setApikeyExpireTime(entity.getApikeyExpireTime());

				return entity;

			}

			return null;
		}

		@Override
		public boolean forgotPassword(UserDTO userDTO) throws InvalidUserException {
			log.info("forgotPassword()- start");
			boolean isSuccess = false;
			String email = userDTO.getEmail();
			User entity = null;
			if (!StringUtils.isEmpty(email)) {
				entity = userRepository.findUserByEmail(email);
				if (null != entity) {
					entity.setPassword(GTGUtils.encode(GTGUtils.getRandomPassword()));
					userRepository.save(entity);

					Set<String> to = new HashSet<String>();
					to.add(entity.getEmail());
					StringBuilder builder = new StringBuilder();
					builder.append("Dear, " + entity.getFirstName() + "<br/>");
					builder.append("Your GTG Portal password has been reset. <br/>");
					builder.append("Please login with your new password : " + GTGUtils.decode(entity.getPassword()));
					emailSenderService.send(to, null, null, "GTG PASSWORD RESET", builder.toString());
					isSuccess = true;
				}
			}

			log.info("forgotPassword()- end");
			return isSuccess;
		}
	
		
	@Override
	public boolean forgotPasswordAsync(UserDTO userDTO) throws InvalidUserException {
		log.info("forgotPasswordAsync()- start");
		boolean isSuccess = false;
		String email = userDTO.getEmail();
		User entity = null;
		if (!StringUtils.isEmpty(email)) {
			entity = userRepository.findUserByEmail(email);
			if (null != entity) {
				entity.setPassword(GTGUtils.encode(GTGUtils.getRandomPassword()));
				userRepository.save(entity);
				Set<String> to = new HashSet<String>();
				to.add(entity.getEmail());
				StringBuilder builder = new StringBuilder();
				builder.append("Dear, " + entity.getFirstName() + "<br/>");
				builder.append("Your GTG Portal password has been reset. <br/>");
				builder.append("Please login with your new password : " + GTGUtils.decode(entity.getPassword()));
				emailSenderService.sendAsync(to, null, null, "GTG PASSWORD RESET", builder.toString());
				isSuccess = true;
			}
		}
		log.info("forgotPassword()- end");
		return isSuccess;
	}

}

}
