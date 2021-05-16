package io.medicorum.auth;

import io.medicorum.auth.models.HealthProfessional;
import io.medicorum.auth.models.Privileges;
import io.medicorum.auth.models.User;
import io.medicorum.auth.repositories.HealthProfessionalRepository;
import io.medicorum.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User healthProfessional = new User();
		healthProfessional.setEmail("doctor@mrdoctor.ma");
		healthProfessional.setFirstName("mrdoctor");
		healthProfessional.setLastName("doctor");
		healthProfessional.setUsername("doctoooor");
		healthProfessional.setPassword("diujfjdkjkdjl");
		HealthProfessional healthProfessional1 = new HealthProfessional(healthProfessional,true);
		healthProfessional1.setProfessionalIdemnitySerialNumber("yuuhjhjk");
		healthProfessional1.setPrivileges(new Privileges(false,false,true,false,true));
		healthProfessional1.roleAssign();
		User justAUSer = new User();
		justAUSer.setEmail("just@User.com");
		justAUSer.setFirstName("just");
		justAUSer.setUsername("user");
		justAUSer.setLastName("just");
		justAUSer.setPassword("user");
		justAUSer.setPrivileges(new Privileges(true,false,false,true,false));
		justAUSer.roleAssign();
		userRepository.save(healthProfessional1);
		userRepository.save(healthProfessional);
		userRepository.save(justAUSer);

		userRepository.findAll().stream().forEach(user -> {
			user.getAssignedRoles().stream().forEach(role -> System.out.println(user.getUsername() + ": "+ role.name()));
		});

	}
}
