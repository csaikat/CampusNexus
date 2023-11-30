package edu.in.mckvie.CampusNexus;


import edu.in.mckvie.CampusNexus.config.*;
import edu.in.mckvie.CampusNexus.entities.*;
import edu.in.mckvie.CampusNexus.repositories.*;
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
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private DepartmentRepositories departmentRepositories;
	@Autowired
	private YearRepositories yearRepositories;
	@Autowired
	private SemesterRepositories semesterRepositories;
	@Autowired
	private FeesRepositories feesRepositories;
	@Autowired
	private UpRoleRepository upRoleRepository;
	public static void main(String[] args) {

		SpringApplication.run(CampusNexusApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

//	@Value("${spring.redis.host}") String redisHost;
//	@Value("${spring.redis.port}") int redisPort;
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
//		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
//		redisConfig.setHostName(redisHost);
//		redisConfig.setPort(redisPort);
//		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisConfig);
//		return jedisConnectionFactory;
//	}

//	@Bean
//	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//		return new StringRedisTemplate(redisConnectionFactory);
//	}
//@Bean
//public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//	RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//	redisTemplate.setConnectionFactory(connectionFactory);
//	redisTemplate.setDefaultSerializer(new StringRedisSerializer());
//	redisTemplate.setKeySerializer(new StringRedisSerializer());
//	redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
//	redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//	redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
//	redisTemplate.afterPropertiesSet();
//	return redisTemplate;
//}

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
		//System.out.println(userRepository.findByUniversityRollNumber("111"));

		//System.out.println(String.valueOf(RoleConstants.ROLE_ADMIN));
		try{
			UpRole ur1=new UpRole();
			ur1.setRole("ADMIN");
			ur1.setId(1);
			UpRole ur2=new UpRole();
			ur2.setRole("STUDENT");
			ur2.setId(2);
			//this.upRoleRepository.saveAll(List.of(ur1,ur2));

			//add default roles
			Role role1=new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName(String.valueOf(RoleConstants.ROLE_ADMIN));
			Role role2=new Role();
			role2.setId(AppConstants.STUDENT_USER);
			role2.setName(String.valueOf(RoleConstants.ROLE_STUDENT));
			Role role3=new Role();
			role3.setId(AppConstants.TEACHER_USER);
			role3.setName(String.valueOf(RoleConstants.ROLE_TEACHER));
			List<Role> roles=this.roleRepository.saveAll(List.of(role1,role2,role3));
			roles.forEach(r-> System.out.println(r.getName()));
			//add default department
			Department dept1=new Department();
			dept1.setDeptName("Computer Science and Engineering");
			Department dept2=new Department();
			dept2.setDeptName("Electronics and Telecommunication Engineering");
			Department dept3=new Department();
			dept3.setDeptName("Electrical Engineering");
			List<Department> depts=this.departmentRepositories.saveAll(List.of(dept1,dept2,dept3));
			depts.forEach(d-> System.out.println(d.getDeptName()));

			//add default year
			Year year1=new Year();
			year1.setName("First Year");
			Year year2=new Year();
			year2.setName("Second Year");
			Year year3=new Year();
			year3.setName("Third Year");
			Year year4=new Year();
			year4.setName("Fourth Year");
			List<Year> years=this.yearRepositories.saveAll(List.of(year1,year2,year3,year4));
			years.forEach(y-> System.out.println(y.getName()));

			//add default fees
			Fees fees1=new Fees();
			fees1.setFees(FeesConstants.FIRST_SEM_FEES);
			Fees fees2=new Fees();
			fees2.setFees(FeesConstants.SECOND_SEM_FEES);
			Fees fees3=new Fees();
			fees3.setFees(FeesConstants.THIRD_SEM_FEES);

			Fees fees4=new Fees();
			fees4.setFees(FeesConstants.FOURTH_SEM_FEES);
			Fees fees5=new Fees();
			fees5.setFees(FeesConstants.FIFTH_SEM_FEES);
			Fees fees6=new Fees();
			fees6.setFees(FeesConstants.SIXTH_SEM_FEES);

			Fees fees7=new Fees();
			fees7.setFees(FeesConstants.SEVENTH_SEM_FEES);
			Fees fees8=new Fees();
			fees8.setFees(FeesConstants.EIGHTH_SEM_FEES);

			List<Fees> fees=this.feesRepositories.saveAll(List.of(fees1,fees2,fees3,fees4,fees5,fees6,fees7,fees8));
			fees.forEach(f-> System.out.println(f.getFees()));

			//add default semester
			Semester sem1=new Semester();
			sem1.setName("First Semester");
			sem1.setFees(fees1);
			sem1.setYear(year1);

			Semester sem2=new Semester();
			sem2.setName("Second Semester");
			sem2.setFees(fees2);
			sem2.setYear(year1);

			Semester sem3=new Semester();
			sem3.setName("Third Semester");
			sem3.setFees(fees3);
			sem3.setYear(year2);

			Semester sem4=new Semester();
			sem4.setName("Fourth Semester");
			sem4.setFees(fees4);
			sem4.setYear(year2);

			Semester sem5=new Semester();
			sem5.setName("Fifth Semester");
			sem5.setFees(fees5);
			sem5.setYear(year3);

			Semester sem6=new Semester();
			sem6.setFees(fees6);
			sem6.setName("Sixth Semester");
			sem6.setYear(year3);

			Semester sem7=new Semester();
			sem7.setName("Seventh Semester");
			sem7.setFees(fees7);
			sem7.setYear(year4);

			Semester sem8=new Semester();
			sem8.setName("Eighth Semester");
			sem8.setFees(fees8);
			sem8.setYear(year4);
			//cmnt this
			//List<Semester> sems=this.semesterRepositories.saveAll(List.of(sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8));
			//sems.forEach(s-> System.out.println(s.getName()));




		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
