package edu.in.mckvie.CampusNexus.services;

import edu.in.mckvie.CampusNexus.entities.User;
import edu.in.mckvie.CampusNexus.payloads.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto register(UserDto userDto);
    Optional<User> getUserForEnroll(String uRoll);
    User setEnroll(User user);
    List<User> getUsers();
    User getUserByMail(String mail);
}
