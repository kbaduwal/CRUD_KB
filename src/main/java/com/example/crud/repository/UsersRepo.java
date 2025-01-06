package com.example.crud.repository;

import com.example.crud.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    //Checking whether that mail have been already used or not
    public Users findByEmail(String email);
}
