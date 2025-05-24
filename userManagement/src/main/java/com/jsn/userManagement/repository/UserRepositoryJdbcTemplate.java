package com.jsn.userManagement.repository;

import com.jsn.userManagement.model.AppUser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJdbcTemplate {


    private final NamedParameterJdbcTemplate jdbc;

    public UserRepositoryJdbcTemplate(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<List<AppUser>> findAllUser() {
        String sql = "SELECT * FROM app_users";
        //Map<String, Object> params = Map.of("id", id);

        try {
            return Optional.of(
                    jdbc.query(sql, new BeanPropertyRowMapper<>(AppUser.class))
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}


//@Repository
//public class UserRepository {
//    private final NamedParameterJdbcTemplate jdbc;
//
//    public UserRepository(NamedParameterJdbcTemplate jdbc) {
//        this.jdbc = jdbc;
//    }
//
//    public Optional<AppUser> findById(int id) {
//        String sql = "SELECT * FROM app_users WHERE id = :id";
//        Map<String, Object> params = Map.of("id", id);
//
//        try {
//            return Optional.ofNullable(
//                    jdbc.queryForObject(sql, params, new BeanPropertyRowMapper<>(AppUser.class))
//            );
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//}