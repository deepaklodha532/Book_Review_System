package com.coding.visit;

import com.coding.visit.entities.Role;
import com.coding.visit.entities.User;
import com.coding.visit.repositories.RoleRepository;
import com.coding.visit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class BookReviewSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookReviewSystemApplication.class, args);
	}

	@Autowired
	RoleRepository roleRepository ;

	@Autowired
	UserRepository userRepository ;

	@Autowired
	PasswordEncoder encode ;

	@Override
	public void run(String... args) throws Exception {
		Role roleAdmin = roleRepository.findByName("ROLE_ADMIN").orElse(null) ;
		if(roleAdmin ==null){
			roleAdmin = new Role();
			roleAdmin.setRoleId(UUID.randomUUID().toString());
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);
		}
		Role roleNormal = roleRepository.findByName("ROLE_NORMAL").orElse(null) ;
		if(roleNormal ==null){
			roleNormal= new Role();
			roleNormal.setRoleId(UUID.randomUUID().toString());
			roleNormal.setName("ROLE_NORMAL");
			roleRepository.save(roleNormal);
		}

		User user1 = userRepository.findByEmail("aneesha@gmail.com").orElse(null) ;
		if(user1 ==null){
			user1 = new User();
			user1.setRoles(Set.of(roleNormal,roleAdmin));
			user1.setName("aneesha");
			user1.setGender("female");
			user1.setPassword(encode.encode("aneesha123"));
			user1.setEmail("aneesha@gmail.com");
			user1.setAbout("this is Aneesha");
			user1.setUserId(UUID.randomUUID().toString());
			userRepository.save(user1);
		}

		User user2 = userRepository.findByEmail("ravina@gmail.com").orElse(null) ;
		if(user2 ==null){
			user2 = new User();
			user2.setRoles(Set.of(roleNormal));
			user2.setName("ravina");
			user2.setGender("female");
			user2.setPassword(encode.encode("ravina123"));
			user2.setEmail("ravina@gmail.com");
			user2.setAbout("this is ravina");
			user2.setUserId(UUID.randomUUID().toString());
			userRepository.save(user2) ;
		}

	}
}
