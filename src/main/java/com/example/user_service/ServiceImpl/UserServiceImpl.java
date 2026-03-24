package com.example.user_service.ServiceImpl;

import com.example.user_service.Entity.User;
import com.example.user_service.Repository.UserRepository;
import com.example.user_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        User savedUser=userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with provided id:"+ id));
    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> new RuntimeException("User not found with username: " + userName));
    }

    @Override
    public List<User> getUserByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> getUserByConatainingKeyword(String data) {
        return userRepository.findByUsernameContaining(data);
    }

    @Override
    public List<User> getUsersSortByUsernameAsc() {
        return userRepository.findAllByOrderByUsernameDesc();
    }

    @Override
    public long countByUserRole(String role) {
        return userRepository.countByRole(role);
    }

    @Override
    public User updateUser(Long id, User user) {
       User existingUser=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with provided id:"+ id));
       existingUser.setUsername(user.getUsername());
       existingUser.setPassword(user.getPassword());
       existingUser.setRole(user.getRole());
       return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with provided id:"+ id));
       Long idPresent=existingUser.getId();
        userRepository.deleteById(idPresent);
    }

    //JPQL QUERIES
  //  ===================================================================================================================
    @Override
    public List<User> getUsersByRoleJP(String role) {
        return userRepository.getUserByRole(role);
    }

    @Override
    public Optional<User> getUserByRoleAndUsernameJP(String role, String username) {
        return userRepository.getUserByRoleAndUsername(role,username);
    }

    @Override
    public User getUserByUserNameNative(String username) {
        return userRepository.getUserNative(username).orElseThrow(()->new RuntimeException("given username not present "+username));
    }

    @Override
    public List<User> getUserWithLikeNative(String keyword) {
        return userRepository.searchUserWithLikeNative(keyword).orElseThrow(()->new RuntimeException("given keyword not matching with username "+keyword));
    }






}
