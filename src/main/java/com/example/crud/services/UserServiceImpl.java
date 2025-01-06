package com.example.crud.services;

import com.example.crud.dto.UserBinding;
import com.example.crud.entity.Books;
import com.example.crud.entity.Users;
import com.example.crud.repository.BooksRepo;
import com.example.crud.repository.UsersRepo;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private BooksRepo booksRepo;

    public UserServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean createUser(UserBinding userBinding) {
        Users user = usersRepo.findByEmail(userBinding.getEmail());

        if(user != null){
            return false;
        }

        Users newUser = new Users();
        BeanUtils.copyProperties(userBinding, newUser);


        String hashedPassword = passwordEncoder.encode(userBinding.getPassword());
        newUser.setPassword(hashedPassword);

        usersRepo.save(newUser);

        return true;
    }

    @Override
    public List<UserBinding> getAllUsers() {
        List<Users> usersList = usersRepo.findAll();
        log.info("Retrieved users: {}", usersList);
        log.info("Retrieved {} users from the database.", usersList.size());

        return usersList.stream()
                .map(users -> new UserBinding(
                        users.getId(),
                        users.getFirstName(),
                        users.getMiddleName(),
                        users.getLastName(),
                        users.getUserName(),
                        users.getEmail(),
                        null // Exclude password for security reasons
                )).toList();
    }

    @Override
    public UserBinding getUserById(Long id) {
       Users user = usersRepo.findById(id).orElseThrow(()->
               new RuntimeException("User not found with id: "+id));

       return new UserBinding(
               user.getId(),
               user.getFirstName(),
               user.getMiddleName(),
               user.getLastName(),
               user.getUserName(),
               user.getEmail(),
               user.getPassword()
       );

    }

    @Override
    public UserBinding updateUser(Long id, UserBinding userBinding) {

        Optional<Users> userDetails = usersRepo.findById(id);

        if(userDetails.isPresent()){
            Users existingUser = userDetails.get();
            BeanUtils.copyProperties(userBinding, existingUser);

            String hashedPassword = passwordEncoder.encode(userBinding.getPassword());
            existingUser.setPassword(hashedPassword);

            Users updatedUser = usersRepo.save(existingUser);

            return new UserBinding(
                    updatedUser.getId(),
                    updatedUser.getFirstName(),
                    updatedUser.getMiddleName(),
                    updatedUser.getLastName(),
                    updatedUser.getUserName(),
                    updatedUser.getEmail(),
                    updatedUser.getPassword()
            );

        }else {
            throw new RuntimeException("User not found with id: "+id);
        }

    }

    @Override
    public void deleteUser(Long id) {
        Optional<Users> userId = usersRepo.findById(id);

        if(userId.isPresent()){
            usersRepo.delete(userId.get());
        }else {
            throw new RuntimeException("User not found with id: "+id);
        }

    }

    // Assign multiple users to a book
    public String assignUsersToBook(Long bookId, List<Long> userIds) {
        Books book = booksRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        for (Long userId : userIds) {
            Users user = usersRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            book.getUsers().add(user);
            user.getBooks().add(book); // Ensure the relationship is bidirectional
        }

        booksRepo.save(book); // Save the updated book with assigned users

        return "Users assigned to book successfully";
    }


}
