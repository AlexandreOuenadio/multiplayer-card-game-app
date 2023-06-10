package com.usermicroservice.model;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
	private List<String> stringList;
	private List<Integer> intList;
	private List<Boolean> boolList;

	@BeforeEach
	public void setUp() {
		System.out.println("[BEFORE TEST] -- Add User to test");
		stringList = new ArrayList<String>();
		intList = new ArrayList<Integer>();
		boolList = new ArrayList<Boolean>();
		stringList.add("normalString1");
		stringList.add("normalString2");
		stringList.add(";:!;!::!;;<>");
		intList.add(5);
		intList.add(500);
		intList.add(-1);
		boolList.add(true);
		boolList.add(false);
	}

	@AfterEach
	public void tearDown() {
		System.out.println("[AFTER TEST] -- CLEAN user list");
		stringList = null;
		intList = null;
		boolList = null;
	}

	@Test
	public void createUser() {
		for(Integer msg1:intList) {
			for(String msg2:stringList) {
				for(String msg3:stringList) {
					for(Integer msg4:intList) {
						for(boolean msg5:boolList) {
							User u = new User(msg1, msg2, msg3, msg4, msg5);
							System.out.println("msg1:"+msg1+", msg2:"+msg2+", msg3:"+msg3+", msg4:"+msg4+", msg5:"+msg5);
							assertTrue(u.getUserId().intValue() == msg1.intValue());
							assertTrue(u.getName() == msg2);
							assertTrue(u.getPassword() == msg3);
							assertTrue(u.getMoney().intValue() == msg4.intValue());	
							assertTrue(u.isNew_user() == msg5);
							
						}	
					}
				}	
			}
		}
	}
	
	@Test
	public void displayUser() {
		User u = new User(1,"jdoe", "password", 100, true);
		String expectedResult="USER [1]: name:jdoe, password:password, money:100 new_user:true";
		assertTrue(u.toString().equals(expectedResult));
	}

}
