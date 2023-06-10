package com.usermicroservice.controller;

import com.DTOLibrary.UserDTO;
import com.usermicroservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.usermicroservice.model.User;
import com.usermicroservice.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService uService;

	@MockBean
	private UserRepository uRepository;

	UserDTO mockUserDTO = new UserDTO(15,"jdoe6", 100);

	@Test
	public void retrieveUserData() throws Exception {
		Mockito.when(
				uService.getUserData(Mockito.anyInt())
				).thenReturn(mockUserDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/50").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());

		String expectedResult="{\"userId\":15,\"name\":\"jdoe6\",\"money\":100,\"password\":null}";

		JSONAssert.assertEquals(expectedResult, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void updateUserMoney() throws Exception {
		Mockito.when(
				uService.getUserData(Mockito.anyInt())
		).thenReturn(mockUserDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateUserMoney")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\":15,\"name\":\"jdoe6\",\"money\":500}");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		ArgumentCaptor<UserDTO> argumentCaptor = ArgumentCaptor.forClass(UserDTO.class);
		Mockito.verify(uService).updateUserMoney(argumentCaptor.capture());
		UserDTO capturedUserDTO = argumentCaptor.getValue();

		assertEquals(500, capturedUserDTO.getMoney());


	}

}




