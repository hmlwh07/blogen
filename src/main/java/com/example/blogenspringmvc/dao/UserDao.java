package com.example.blogenspringmvc.dao;

import com.example.blogenspringmvc.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users,Integer> {
}
