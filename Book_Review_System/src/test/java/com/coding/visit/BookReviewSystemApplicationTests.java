package com.coding.visit;

import com.coding.visit.entities.User;
import com.coding.visit.repositories.UserRepository;
import com.coding.visit.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class BookReviewSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepository ;

	@Test
	public void testing(){
		User user = userRepository.findByEmail("ravina@gmail.com").get() ;
		String token = jwtUtil.generateToken(user) ;
		System.out.println("\n\n\n");
		System.out.println(token);
		System.out.println("\n\n\n");

		System.out.println(jwtUtil.getUsernameFromToken(token));
		System.out.println("\n\n\n");

		System.out.println(jwtUtil.isTokenExpired(token));
		System.out.println("\n\n\n");


	}

}
