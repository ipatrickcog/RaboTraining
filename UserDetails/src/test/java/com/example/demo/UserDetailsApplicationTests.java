package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.UserDetails.UserDetailsApplication;
import com.cts.UserDetails.model.User;
import com.cts.UserDetails.repo.UserRepo;
import com.cts.UserDetails.service.UserDetailsService;

@SpringBootTest(classes = UserDetailsApplication.class)
class UserDetailsApplicationTests {
	
	private User u;
	
	@Mock
	private UserRepo userRepo;
	
	@InjectMocks
	private UserDetailsService service = new UserDetailsService();
	
	
	@BeforeEach
	public void setMockOutput() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			u = new User("1", "martinpullman9", "martpull123", "Martin Pullman", 23, formatter.parse("1999-04-27"), "Springweg 25", "martpulls@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		when(userRepo.findByUserId("1")).thenReturn(u);
	}
	
	
	@Test
	public void testFindByUserId() {
		assertEquals(userRepo.findByUserId("1"), u);
	}

}
