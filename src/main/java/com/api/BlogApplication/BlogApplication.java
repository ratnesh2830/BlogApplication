package com.api.BlogApplication;

import com.api.BlogApplication.Entities.Role;
import com.api.BlogApplication.Repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private  RoleRepo roleRepo;


	public static void main(String[] args) {

		SpringApplication.run(BlogApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception
	{
		try {

			Role role =new Role();
			role.setId(501);
			role.setName("ROLE_NORMAL");

			Role role1 = new Role();
			role1.setId(502);
			role1.setName("ROLE_ADMIN");

			List<Role> roles =List.of(role,role1);
			roleRepo.saveAll(roles);

		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
