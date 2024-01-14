package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.entities.User;
import edu.in.mckvie.CampusNexus.exceptions.AuthException;
import edu.in.mckvie.CampusNexus.payloads.ApiResponse;
import edu.in.mckvie.CampusNexus.payloads.JwtAuthRequest;
import edu.in.mckvie.CampusNexus.payloads.JwtAuthResponse;
import edu.in.mckvie.CampusNexus.payloads.UserDto;
import edu.in.mckvie.CampusNexus.security.JwtTokenHelper;
import edu.in.mckvie.CampusNexus.services.ConcurrentLoginInterceptorService;
import edu.in.mckvie.CampusNexus.services.servicesimpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ConcurrentLoginInterceptorService concurrentLoginInterceptorService;

    @PostMapping("/authenticate/login")
    public ResponseEntity<?> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        int roleId=this.userService.getUserByMail(request.getUsername()).getRole1().getId();
        System.out.println("id:"+roleId);
        Authentication authentication=this.authenticate(request.getUsername(),request.getPassword(),roleId,2);
        if(authentication.isAuthenticated()){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
            String username=userDetails.getUsername();
            if(concurrentLoginInterceptorService.contain(username)){
                return new ResponseEntity<ApiResponse>
                        (new ApiResponse("Session already active with username :"+username,false),HttpStatus.UNAUTHORIZED);
            }
            //generate token
            String token= this.jwtTokenHelper.generateToken(userDetails);
            //intercept
            concurrentLoginInterceptorService.saveData(username, token);
//            concurrentLoginInterceptorService.blacklistToken(username);
            JwtAuthResponse response=new JwtAuthResponse();
            response.setToken(token);
            response.setUser(this.modelMapper.map((User)userDetails,UserDto.class));
            return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
        }
       else{
           throw new UsernameNotFoundException("invalid user request!!");
        }
    }
    //admin login
    @PostMapping("/authenticate/admin-login")
    public ResponseEntity<?> createToken1(@RequestBody JwtAuthRequest request) throws Exception {
        int roleId=this.userService.getUserByMail(request.getUsername()).getRole1().getId();
        System.out.println("id:"+roleId);
        Authentication authentication=this.authenticate(request.getUsername(),request.getPassword(),roleId,1);
        if(authentication.isAuthenticated()){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
            String username=userDetails.getUsername();
            if(concurrentLoginInterceptorService.contain(username)){
                return new ResponseEntity<ApiResponse>
                        (new ApiResponse("Session already active with username :"+username,false),HttpStatus.UNAUTHORIZED);
            }
            //generate token
            String token= this.jwtTokenHelper.generateToken(userDetails);
            //intercept
            concurrentLoginInterceptorService.saveData(username, token);
//            concurrentLoginInterceptorService.blacklistToken(username);
            JwtAuthResponse response=new JwtAuthResponse();
            response.setToken(token);
            response.setUser(this.modelMapper.map((User)userDetails,UserDto.class));
            return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
        }
        else{
            throw new UsernameNotFoundException("invalid user request!!");
        }
    }
    @PostMapping("/authenticate/isActive")
    public ResponseEntity<?> isKeyExits(@RequestBody JwtAuthRequest request){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String username=userDetails.getUsername();

        System.out.println("pp"+username);
        if(concurrentLoginInterceptorService.contain(username)){
            return new ResponseEntity<>(new ApiResponse("Key Exists",true),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ApiResponse("Key Not Exists",false),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody User request, HttpServletRequest r) {
        String authorizationHeader = r.getHeader("Authorization");
        System.out.println("header:"+authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            concurrentLoginInterceptorService.deleteData(request.getUsername());
        }
        return new ResponseEntity<>(new ApiResponse("Logout Successfully",true),HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password,int roleId,int type) throws Exception {
        if(type==1){
            if(roleId!=2){
                Authentication authentication=null;
                UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken(username,password);
                try {
                    authentication= this.authenticationManager.authenticate(authenticationToken);
                }
                catch (BadCredentialsException e) {
                    throw new AuthException("Invalid Username And Password !!");
                }
                return authentication;
            }
            else{
                throw new AuthException("Student can not log in here!!");
            }
        }
        else if(type==2){
            if(roleId!=1){
                Authentication authentication=null;
                UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken(username,password);
                try {
                    authentication= this.authenticationManager.authenticate(authenticationToken);
                }
                catch (BadCredentialsException e) {
                    throw new AuthException("Invalid Username And Password !!");
                }
                return authentication;
            }
            else{
                throw new AuthException("Admin can not log in here!!");
            }
        }
        else{
            throw new AuthException("unknown type !!");
        }
    }
    @PostMapping("/authenticate/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto){
        UserDto userDto1=this.userService.register(userDto);
        return new ResponseEntity<UserDto>(userDto1, HttpStatus.CREATED);
    }
}
