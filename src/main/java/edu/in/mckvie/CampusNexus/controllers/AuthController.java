package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.exceptions.AuthException;
import edu.in.mckvie.CampusNexus.payloads.JwtAuthRequest;
import edu.in.mckvie.CampusNexus.payloads.JwtAuthResponse;
import edu.in.mckvie.CampusNexus.payloads.UserDto;
import edu.in.mckvie.CampusNexus.security.JwtTokenHelper;
import edu.in.mckvie.CampusNexus.services.servicesimpl.UserServiceImpl;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        Authentication authentication=this.authenticate(request.getUsername(),request.getPassword());
        if(authentication.isAuthenticated()){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
            String token= this.jwtTokenHelper.generateToken(userDetails);
            JwtAuthResponse response=new JwtAuthResponse();
            response.setToken(token);
            return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
        }
       else{
           throw new UsernameNotFoundException("invalid user request!!");
        }
    }

    private Authentication authenticate(String username, String password) throws Exception {
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
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto){
        UserDto userDto1=this.userService.register(userDto);
        return new ResponseEntity<UserDto>(userDto1, HttpStatus.CREATED);
    }
}
