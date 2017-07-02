package com.dreamproject;

import com.dreamproject.dao.RoleDao;
import com.dreamproject.dao.UserDao;
import com.dreamproject.entity.User;
import com.dreamproject.entity.security.Role;
import com.dreamproject.entity.security.UserRole;
import com.dreamproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DreamProjectApplication implements CommandLineRunner {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DreamProjectApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {


		Role role1 = new Role();
		role1.setName("ROLE_ADMIN");
		roleDao.save(role1);

		Role role2 = new Role();
		role2.setName("ROLE_USER");
		roleDao.save(role2);

		User user1 = new User();
		user1.setFirstName("Michail");
		user1.setLastName("Drazdovich");
		user1.setUsername("mdrazdovich@blazemeter.com");
		user1.setPassword("210280");
		Set<UserRole> userRoles1 = new HashSet<>();
		userRoles1.add(new UserRole(user1, roleDao.findByName("ROLE_ADMIN")));
		userService.createUser(user1, userRoles1);

		User user2 = new User();
		user2.setFirstName("Galina");
		user2.setLastName("Drazdovich");
		user2.setUsername("galinka.minsk@gmail.com");
		user2.setPassword("210280");
		Set<UserRole> userRoles2 = new HashSet<>();
		userRoles2.add(new UserRole(user2, roleDao.findByName("ROLE_USER")));
		userService.createUser(user2, userRoles2);
	}
}
