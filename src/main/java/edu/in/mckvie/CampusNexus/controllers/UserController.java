package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.payloads.ApiResponse;
import edu.in.mckvie.CampusNexus.payloads.UserDto;
import edu.in.mckvie.CampusNexus.services.servicesimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/")
    ResponseEntity<ApiResponse> getUser(@RequestBody UserDto userDto){
        String message=this.userService.getUser(userDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse(message,true), HttpStatus.OK);
    }
    @PutMapping("/update-password")
    ResponseEntity<ApiResponse> updatePassword(@RequestBody UserDto userDto){
        String message=this.userService.updatePassword(userDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse(message,true), HttpStatus.OK);
    }
    @PostMapping("/send-otp")
    ResponseEntity<ApiResponse> sendOtpEmail(@RequestBody UserDto userDto){
        String message=this.userService.sendOtpEmail(userDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse(message,true),HttpStatus.OK);
    }


}
