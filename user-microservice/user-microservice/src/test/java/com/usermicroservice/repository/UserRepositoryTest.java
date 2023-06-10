package com.usermicroservice.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.usermicroservice.model.User;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	UserRepository urepo;

	@BeforeEach
	public void setUp() {
		urepo.save(new User(1,"jdoe", "password", 100, true));
	}

	@AfterEach
	public void cleanUp() {
		urepo.deleteAll();
	}

	@Test
	public void saveUser() {
		urepo.save(new User(2,"jdoe2", "password2", 200, false));
		assertTrue(true);
	}

	@Test
	public void saveAndGetUser() {
		urepo.deleteAll();
		urepo.save(new User(3,"jdoe2", "password2", 200, false));
		List<User> UserList = new ArrayList<>();
		urepo.findAll().forEach(UserList::add);
		assertTrue(UserList.size() == 1);
		assertTrue(UserList.get(0).getName().equals("jdoe2"));
		assertTrue(UserList.get(0).getPassword().equals("password2"));
		assertTrue(UserList.get(0).getMoney() == 200);
	}



	@Test
	public void findByNameAndPassword() {
		List<User> list = new ArrayList<User>();
		urepo.save(new User(10,"jdoe4", "password1", 200, false));
		urepo.save(new User(11,"jdoe4", "password2", 200, false));
		User user = urepo.findByNameAndPassword("jdoe4","password2");
		list.add(user);
		assertTrue(list.size() == 1);
	}


}
