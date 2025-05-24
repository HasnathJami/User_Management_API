package com.jsn.userManagement.controller;

import com.jsn.userManagement.model.AppUser;
import com.jsn.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(path = "users", produces = {"application/json"})
//    @ResponseBody
    public List<AppUser> getUsers() {
        List<AppUser> users = userService.getUsers();
        return users;
    }

    //    @PostMapping(path = "user", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PostMapping(path = "user", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public AppUser addUser(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "mobile_phone", required = false) Long phone, @RequestHeader(value = "device_type", required = false, defaultValue = "jamiHeader") String deviceType) {
    public AppUser addUser(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(required = false) Long phone, @RequestHeader(value = "device_type", required = false, defaultValue = "jamiHeader") String deviceType) {
        return userService.addUser(name, email, phone);
    }

    @PutMapping(path = "user/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable long id, @RequestParam String name, @RequestParam String email, @RequestParam Long phone) {
        return userService.updateUser(id, name, email, phone);
    }

    @GetMapping("user/id/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("user/name/{name}")
    public ResponseEntity<AppUser> getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PostMapping(path = "userBody",  consumes = {"application/json"})
    public AppUser addUserBody(@RequestBody AppUser u) {
        userService.addUserBody(u);
        return u;
    }

}
