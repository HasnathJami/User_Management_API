package com.jsn.userManagement.controller;

import com.jsn.userManagement.model.AppUser;
import com.jsn.userManagement.repository.UserRepositoryJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class UserControllerJdbcTemplate {

    @Autowired
    UserRepositoryJdbcTemplate userRepositoryJdbc;

    /// ////////////////////////////////// JDBC PART ///////////////////////////////
    @GetMapping(path = "usersJdbcTemplate", produces = {"application/json"})
//    @ResponseBody
    public List<AppUser> getUsersJdbc() {
        Optional<List<AppUser>> usersOpt = userRepositoryJdbc.findAllUser();
        List<AppUser> users = new ArrayList<>();
        if (!usersOpt.isEmpty()) {
            users = usersOpt.get();
        }
        return users;
    }

}
