package com.crowdfunding_paradigm.platform.services.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crowdfunding_paradigm.platform.services.exceptions.ResourceNotFoundException;
import com.crowdfunding_paradigm.platform.services.role.Role;
import com.crowdfunding_paradigm.platform.services.role.RoleRepository;
import com.crowdfunding_paradigm.platform.services.role.RoleType;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

    @Autowired
	private RoleRepository roleRepository;

	@Transactional
	@Override
	public UserDTO createUser(UserDTO dto) {
		User user = modelMapper.map(dto, User.class);
		
		Role foundRole = roleRepository.findByName(RoleType.ADMIN);
		if (foundRole == null)
			throw new RuntimeException("Role not found");
		user = userRepo.save(user);
		user.setRole(Arrays.asList(new UserRole()));

		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userId) {

		User getUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		getUser.setAbout(user.getAbout());
		getUser.setEmail(user.getEmail());
		getUser.setName(user.getName());
		getUser.setMobileNumber(user.getMobileNumber());
		User updatedUser = this.userRepo.save(getUser);
		return this.modelMapper.map(updatedUser, UserDTO.class);

	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.modelMapper.map(user, UserDTO.class);

	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> allUsers = this.userRepo.findAll();
		List<UserDTO> result = allUsers.stream().map(user -> this.modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
	}



}
