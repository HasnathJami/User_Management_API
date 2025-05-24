package com.jsn.userManagement.repository;

import com.jsn.userManagement.model.AppUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDB {
    Connection conn;

    public UserDB() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdb", "postgres", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AppUser> fetchUsers() {
        List<AppUser> users = new ArrayList<>();
        String query = "select name,email,phone from app_users";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                AppUser user = new AppUser();
                user.setName(rs.getString(1));
                user.setEmail(rs.getString(2));
                user.setPhone(rs.getLong(3));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void saveUser(AppUser user) {
        String query = "insert into app_users (name, email, phone) values(?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setLong(3, user.getPhone());
            st.execute();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
