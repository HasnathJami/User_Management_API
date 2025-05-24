package com.jsn.userManagement.controller;

import com.jsn.userManagement.model.AppUser;
import com.jsn.userManagement.repository.UserRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/")
public class UserControllerJdbc {

    @Autowired
    UserRepositoryJdbc userRepositoryJdbc;

    /// ////////////////////////////////// JDBC PART ///////////////////////////////
    @GetMapping(path = "usersJdbc", produces = {"application/json"})
//    @ResponseBody
    public List<AppUser> getUsersJdbc() {
        List<AppUser> users = userRepositoryJdbc.getUsers();
        return users;
    }

}

