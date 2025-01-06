package com.example.crud.services;
import com.example.crud.dto.BookBinding;
import com.example.crud.dto.UserBinding;
import com.example.crud.entity.Books;

import java.util.List;
//CRUD

public interface UserService {
    public boolean createUser(UserBinding userBinding);

    public List<UserBinding> getAllUsers();

    public UserBinding getUserById(Long id);

    public UserBinding updateUser(Long id, UserBinding userBinding);

    public void deleteUser(Long id);

    public String assignUsersToBook(Long bookId, List<Long> userIds);
}
