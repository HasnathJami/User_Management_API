package com.jsn.userManagement.controller;

import com.jsn.userManagement.model.AppUser;
import com.jsn.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class UserController {

    @Autowired
    UserRepository userRepository;

//    UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @GetMapping(path = "users", produces = {"application/json"})
//    @ResponseBody
    public List<AppUser> getUsers() {
        List<AppUser> users = userRepository.findAll();
        return users;
    }

//    @PostMapping("user")
//    public AppUser addUser(@RequestBody AppUser u) {
//        userRepository.save(u);
//        return u;
//    }

    //    @PostMapping(path = "user", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PostMapping(path = "user", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public AppUser addUser(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "mobile_phone", required = false) Long phone, @RequestHeader(value = "device_type", required = false, defaultValue = "jamiHeader") String deviceType) {
    public AppUser addUser(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(required = false) Long phone, @RequestHeader(value = "device_type", required = false, defaultValue = "jamiHeader") String deviceType) {
        System.out.println(deviceType);
        AppUser user = new AppUser();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
//        user.setPhone(phone);
        userRepository.save(user);
        return user;
    }

    @PutMapping(path = "user/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable long id, @RequestParam String name, @RequestParam String email, @RequestParam Long phone) {
        Optional<AppUser> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();
            user.setName(name);  // update the name
            user.setEmail(email);  // update the name
            user.setPhone(phone);  // update the name
            userRepository.save(user);  // save changes
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable long id) {
        Optional<AppUser> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        Optional<AppUser> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.deleteById(id);
//            return ResponseEntity.ok(userOpt.get());
            return ResponseEntity.ok(id + "Deleted Sucessfully");
//            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
