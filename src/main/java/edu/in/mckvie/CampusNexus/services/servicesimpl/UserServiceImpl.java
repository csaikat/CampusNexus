package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.config.AppConstants;
import edu.in.mckvie.CampusNexus.entities.DefaulterList;
import edu.in.mckvie.CampusNexus.entities.Role;
import edu.in.mckvie.CampusNexus.entities.User;
import edu.in.mckvie.CampusNexus.exceptions.ResourceNotFoundException;
import edu.in.mckvie.CampusNexus.payloads.MailDTO;
import edu.in.mckvie.CampusNexus.payloads.UserDto;
import edu.in.mckvie.CampusNexus.repositories.DefaulterListRepository;
import edu.in.mckvie.CampusNexus.repositories.RoleRepository;
import edu.in.mckvie.CampusNexus.repositories.UserRepository;
import edu.in.mckvie.CampusNexus.services.MailService;
import edu.in.mckvie.CampusNexus.services.UserService;
import edu.in.mckvie.CampusNexus.utils.OtpGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private DefaulterListRepository defaulterListRepository;

    @Override
    public UserDto register(UserDto userDto) {
        System.out.println(userDto);
        User user = this.modelMapper.map(userDto, User.class);
        System.out.println(user);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.PASSWORD_PATTERN);
        String date = simpleDateFormat.format(user.getDob());
        user.setPassword(passwordEncoder.encode(date));
        System.out.println(user);
        Role role = this.roleRepository.findById(AppConstants.STUDENT_USER).get();
        user.getRoles().add(role);
        User newUser = this.userRepository.save(user);
        //add in defulter list also
        DefaulterList defaulterList=new DefaulterList();
        defaulterList.setUser(newUser);
        this.defaulterListRepository.save(defaulterList);

        return this.modelMapper.map(newUser, UserDto.class);
    }

    public String getUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        User foundUser = this.userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("user not found", "email", user.getEmail()));
        return "User Found";

    }
    public String updatePassword(UserDto userDto) {
        System.out.println(userDto);
        User user = this.modelMapper.map(userDto, User.class);
        System.out.println(user);
        User foundUser = this.userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("user not found", "email", user.getEmail()));
        foundUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User updateUser = this.userRepository.save(foundUser);
        return "update password successfully";
    }

    public String sendOtpEmail(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        User foundUser = this.userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("user not found", "email", user.getEmail()));
        String otp=OtpGenerator.getOTP(6);
        MailDTO mailDTO=new MailDTO();
        mailDTO.setTo(foundUser.getEmail());
        mailDTO.setSubject("OTP For Password Change");
        mailDTO.setMessage("Your Otp for password change is: "+ otp);
        if(this.mailService.sendMail(mailDTO))
            return otp;
        else
            return "some problem";

    }



}