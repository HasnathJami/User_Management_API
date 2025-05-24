package com.jsn.userManagement.repository;

import com.jsn.userManagement.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryJdbc {

    public UserDB userDB = new UserDB();

    public void saveUser(AppUser user) {
        userDB.saveUser(user);
    }

    public List<AppUser> getUsers() {
        return userDB.fetchUsers();
    }
}
