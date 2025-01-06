package com.example.crud.repository;

import com.example.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {

    //Checking whether that mail have been already used or not
    public User findByEmail(String email);
}
