package com.example.user_service.Service;

import com.example.user_service.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //To create user
    public User createUser(User user);

    //to get all users
    public List<User> getAllUsers();

    // //to get  users by id
    public User getUserById(Long id);

    //to get  users by name
    public User getUserByName(String name);

    //to get user by role
    public List<User> getUserByRole(String role);

    //to get user name containing particular string
    public List<User> getUserByConatainingKeyword(String data);

    //Find users sorted by username
    public List<User> getUsersSortByUsernameAsc();

    // Count users by role
    long countByUserRole(String role);

    //to update user
    User updateUser(Long id,User user);

    //To delete user
    void deleteUser(Long id);

                                    //JPQL QUERIES
    //============================================================================================

    //1. getUserByRole
    public List<User> getUsersByRoleJP(String role);

    //2. JPQL with multiple conditions
    public Optional<User> getUserByRoleAndUsernameJP(String role,String username);

                                      //Native QUERIES
    //============================================================================================================
    public User getUserByUserNameNative(String username);

    //Native Query with LIKE
    public List<User> getUserWithLikeNative(String keyword);
}
