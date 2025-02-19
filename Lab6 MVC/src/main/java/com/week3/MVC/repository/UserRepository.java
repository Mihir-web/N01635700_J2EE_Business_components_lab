package com.week3.MVC.repository;


import com.week3.MVC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findByUserName(String userName);
}
