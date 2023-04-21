package edu.in.mckvie.CampusNexus;


import edu.in.mckvie.CampusNexus.config.AppConstants;
import edu.in.mckvie.CampusNexus.entities.Role;
import edu.in.mckvie.CampusNexus.repositories.RoleRepository;
import edu.in.mckvie.CampusNexus.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CampusNexusApplication implements CommandLineRunner {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {

		SpringApplication.run(CampusNexusApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
//		Role r=new Role();
//		r.setName("ROLE_ADMIN");
//		User u=new User();
//		u.setName("monish");
//		u.setUniversityRollNumber("111");
//		u.setEmail("monish1.paul2000@gmail.com");
//		u.setPassword(this.passwordEncoder.encode("xyz"));
//		Set<Role> role=new HashSet<>();
//		role.add(r);
//		u.setRoles(role);
//		this.userRepository.save(u);
//		System.out.println(userRepository.findByUniversityRollNumber("111"));
//
		try{
			Role role1=new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ROLE_ADMIN");
			Role role2=new Role();
			role2.setId(AppConstants.STUDENT_USER);
			role2.setName("ROLE_STUDENT");
			List<Role> roles=this.roleRepository.saveAll(List.of(role1,role2));
			roles.forEach(r-> System.out.println(r.getName()));
		}catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
