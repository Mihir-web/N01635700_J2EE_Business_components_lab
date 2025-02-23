package com.week3.MVC.repository;

import com.week3.MVC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByUserName(String userName);

    // Changed the method name to findById
    public Optional<User> findById(Long id);

    List<User> findByIsAdminAndIsDelete(int isAdmin, int isDelete);

    @Query("SELECT u FROM User u WHERE u.isDelete = 0 AND u.isAdmin = 0")
    List<User> getAllNonAdminUsers();
}
