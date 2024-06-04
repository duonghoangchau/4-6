package com.example.bonsau.Repository;

import com.example.bonsau.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    //@Query("select u from User u where u.Email=?1")
    //User findByEmail(String email);
}
