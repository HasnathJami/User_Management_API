package com.jsn.userManagement.repository;

import com.jsn.userManagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<AppUser, Long> {
}