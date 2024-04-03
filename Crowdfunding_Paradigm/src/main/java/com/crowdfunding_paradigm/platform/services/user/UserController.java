package com.crowdfunding_paradigm.platform.services.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crowdfunding_paradigm.platform.services.common.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("Crowdfunding_Paradigm/api/user/operation")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {
		UserDTO createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}


	@PutMapping("/updateUserById/{userId}")
	public ResponseEntity<UserModel> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable Integer userId) {
		UserDTO updatedUser = this.userService.updateUser(userDto, userId);
		UserModel model = null ;
		model.setName(updatedUser.getName());
		model.setEmail(updatedUser.getEmail());
		model.setId(updatedUser.getId());
		return new ResponseEntity<UserModel>(model, HttpStatus.OK);
	}


	@DeleteMapping("/deleteById/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);

	}


	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());

	}

	
	@GetMapping("/userById/{userId}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable Integer userId) {

		return ResponseEntity.ok(this.userService.getUserById(userId));

	}

	
}