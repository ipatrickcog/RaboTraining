package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.cts.UserDetails.UserDetailsApplication;

@SpringBootTest(classes = UserDetailsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserDetailsControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUser1Details() throws Exception {

		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/details/1").toString(), String.class);
		assertEquals("{\"userId\":\"1\",\"username\":\"martinpullman9\",\"password\":\"martpull123\",\"fullname\":\"Martin Pullman\",\"age\":23,\"dob\":\"1999-04-26T22:00:00.000+00:00\",\"address\":\"Springweg 25\",\"email\":\"martpulls@gmail.com\"}", response.getBody());

	}
	
	@Test
	public void getUser2Details() throws Exception {

		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/details/2").toString(), String.class);
		assertEquals("{\"userId\":\"2\",\"username\":\"johncuisine00\",\"password\":\"jcuzz987\",\"fullname\":\"John Cuisine\",\"age\":24,\"dob\":\"1998-05-16T22:00:00.000+00:00\",\"address\":\"Haverstraat 125\",\"email\":\"john_cooks@hotmail.com\"}", response.getBody());

	}
	
	@Test
	public void getUser3Details() throws Exception {

		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/details/3").toString(), String.class);
		assertEquals("{\"userId\":\"3\",\"username\":\"zackabe5\",\"password\":\"zackyabraham77\",\"fullname\":\"Zac Abbey\",\"age\":25,\"dob\":\"1996-07-06T22:00:00.000+00:00\",\"address\":\"Herenstraat 42\",\"email\":\"izaackey@gmail.com\"}", response.getBody());

	}
}
