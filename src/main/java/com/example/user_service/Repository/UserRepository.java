package com.example.user_service.Repository;

import com.example.user_service.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String userName);
    List<User> findByRole(String role);
    List<User> findByUsernameContaining(String name);
    List<User> findAllByOrderByUsernameDesc(); //For Ascending use Asc
    // 8. Count users by role
    long countByRole(String role);

                                       //JPQL QUERIES
    //============================================================================================================


    //1. getUserByRole
    @Query("select u from User u where u.role=:role")
    public List<User> getUserByRole(@Param("role") String role);

     //2. JPQL with multiple conditions
    @Query("select u from User u where u.role=:role and u.username=:username")
    Optional<User> getUserByRoleAndUsername(@Param("role") String role,@Param("username") String username);

                                     //Native QUERIES
    //============================================================================================================
    // in postgres user is reserved keyword so we need to enclose with \"
    @Query(value="select * from users where username=:username",nativeQuery = true)
    Optional<User> getUserNative(@Param("username") String username);

    //Native Query with LIKE
    @Query(value = "select * from users where username like %:keyword%",nativeQuery = true)
    Optional<List<User>> searchUserWithLikeNative(@Param("keyword") String keyword);
}
