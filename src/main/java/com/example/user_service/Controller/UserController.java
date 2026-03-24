package com.example.user_service.Controller;

import com.example.user_service.Entity.User;
import com.example.user_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    //to create user
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        System.out.println("I am from controller class");
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    //To get all users
    @GetMapping("/getAllusers")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    //To get user by id
    @GetMapping("/getUserbyId/{id}")
    public ResponseEntity<User> getUserbyId(@PathVariable Long id)
    {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
    //To get user by name
    @GetMapping("/getUserbyName/{name}")
    public ResponseEntity<User> getUserbyName(@PathVariable String name)
    {
        return new ResponseEntity<>(userService.getUserByName(name),HttpStatus.OK);
    }

    //To get user by role
    @GetMapping("/getUserByRole/{role}")
    public ResponseEntity<?> getUserByRole(@PathVariable String role)
    {
        List<User> user=userService.getUserByRole(role);
        if(user.isEmpty())
        {
            return new ResponseEntity<>("Given role is not present: "+role,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //to get user name containing particular string
    @GetMapping("/getUserByUsernamecontaining/{name}")
    public ResponseEntity<?> getUserByUsernamecontaining(@PathVariable String name)
    {
        List<User> user=userService.getUserByConatainingKeyword(name);
        if(user.isEmpty())
        {
            return new ResponseEntity<>("No user containing given string in their username: "+name,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    // Find users sorted by username
    @GetMapping("/getUsersbyUsernameAsc")
    public ResponseEntity<List<User>> getUsersbyUsernameAsc()
    {
        return new ResponseEntity<>(userService.getUsersSortByUsernameAsc(),HttpStatus.OK);
    }

    // Get users count by role
    @GetMapping("/getUserscountByRole/{role}")
    public ResponseEntity<Long> getUserscountByRole(@PathVariable String role)
    {
        return new ResponseEntity<>(userService.countByUserRole(role),HttpStatus.OK);
    }

    //To update user
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user)
    {
        return new ResponseEntity<>(userService.updateUser(id,user),HttpStatus.OK);
    }

    //To delete user
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return  ResponseEntity.ok("User deleted successfully with Id:"+id);
    }

                                                            //JPQL QUERIES
    //============================================================================================================

    //1. Get Users By Role using JPQL
    @GetMapping("/getUserbyRoleJP/{role}")
    public ResponseEntity<List<User>> getUserbyRoleJP(@PathVariable String role)
    {
        return new ResponseEntity<>(userService.getUsersByRoleJP(role),HttpStatus.OK);
    }

    //2. Get Users By Role and username using JPQL
    @GetMapping("/getUserByRoleAndUsernameJP/{role}/{username}")
    public ResponseEntity<?> getUserByRoleAndUsernameJP(@PathVariable String role,@PathVariable String username)
    {

        Optional<User> user=userService.getUserByRoleAndUsernameJP(role,username);
        System.out.println("=========================");
        System.out.println(user);
        if(user.isEmpty())
        {
            return new ResponseEntity<>("Given role and username didn't match: "+role+" "+username,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

                                                 //Native QUERIES
    //============================================================================================================@GetMapping("/getUserbyRoleJP")
    //1. Get Users By username  Native QUERIES
    @GetMapping("/getUserByUsernameNative/{username}")
    public ResponseEntity<User> getUserByUsernameNative(@PathVariable String username)
        {
            return new ResponseEntity<>(userService.getUserByUserNameNative(username),HttpStatus.OK);
        }

    // Get Users By username Native Query with LIKE
    @GetMapping("/getUserByUsernameWithLikeNative/{keyword}")
    public ResponseEntity<List<User>> getUserByUsernameWithLikeNative(@PathVariable String keyword)
    {
        return new ResponseEntity<>(userService.getUserWithLikeNative(keyword),HttpStatus.OK);
    }

}
//Imp points to remember
    /* How to fix (recommended)
    Let repository always return Optional<User> (you already do)
    Let service return Optional<User>`, do not throw.
    Let controller check isEmpty() and return 404 if missing. */