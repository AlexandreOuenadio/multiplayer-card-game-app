package com.usermicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.DTOLibrary.CardUserDTO;
import com.DTOLibrary.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.usermicroservice.model.User;
import com.usermicroservice.repository.UserRepository;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserService.class)
public class UserServiceTest {
		
	@MockBean
	private UserRepository hRepo;
	
	@Autowired
	private UserService hService;
	
	User tmpUser = new User(10,"jdoe5", "password", 100, true);

	@Test
	public void getUser() {
		Mockito.when(
				hRepo.findById(Mockito.any())
				).thenReturn(Optional.ofNullable(tmpUser));
		User userInfo = hService.getUser(15);
		assertTrue(userInfo.toString().equals(tmpUser.toString()));
	}

	@Test
	public void getUserData() {
		Mockito.when(
				hRepo.findById(Mockito.any())
		).thenReturn(Optional.ofNullable(tmpUser));
		UserDTO userDTO = hService.getUserData(20);
		assertEquals(userDTO.getUserId(), tmpUser.getUserId());
		assertEquals(userDTO.getName(), tmpUser.getName());
		assertEquals(userDTO.getPassword(), tmpUser.getPassword());
	}

	@Test
	public void updateUserMoney() {
		Mockito.when(
				hRepo.findById(Mockito.any())
		).thenReturn(Optional.ofNullable(tmpUser));
		UserDTO userDTO = hService.getUserData(10);
		userDTO.setMoney(500);
		assertEquals(userDTO.getMoney(), 500);
	}

	@Test
	public void userExist() {
		boolean userExists = hService.userExist("jdoe5");
		assertEquals(userExists, true);
	}

	@Test
	public void register() {
		UserDTO newUserDTO = new UserDTO(20,"johnsmith",200);
		Mockito.when(hRepo.findByName(Mockito.any())).thenReturn(null);
		hService.register(newUserDTO);
		Mockito.verify(hRepo, Mockito.times(1)).save(Mockito.any(User.class));
	}

	@Test
	public void login() {
		Mockito.when(hRepo.findByNameAndPassword(Mockito.any(), Mockito.any())).thenReturn(tmpUser);
		User user = hService.login(new UserDTO(15,"jdoe5", 100));
		assertEquals(user.getName(), tmpUser.getName());
		assertEquals(user.getPassword(), tmpUser.getPassword());
	}

}

