package com.jsn.userManagement.service;

import com.jsn.userManagement.model.AppUser;
import com.jsn.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    //If we dont want to user autowiring then add below two lines
//    UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public AppUser addUser(String name, String email, Long phone) {
        AppUser user = new AppUser();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        userRepository.save(user);
        return user;
    }

    public AppUser addUserBody(AppUser user) {
        userRepository.save(user);
        return user;
    }

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<AppUser> getUserById(Long id) {
        Optional<AppUser> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<AppUser> getUserByName(String name) {
        Optional<AppUser> userOpt = userRepository.findByName(name);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<AppUser> updateUser(Long id, String name, String email, Long phone) {
        Optional<AppUser> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();
            user.setName(name);  // update the name
            user.setEmail(email);  // update the name
            user.setPhone(phone);  // update the name
            userRepository.save(user);  // save changes
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> deleteUser(Long id) {
        Optional<AppUser> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok(id + " Deleted Successfully");
        }
        return ResponseEntity.notFound().build();
    }


}
