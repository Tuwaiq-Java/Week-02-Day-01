package com.example.usersmanagementsoftware.repository;

import com.example.usersmanagementsoftware.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);

    List<User> findByAgeGreaterThan(Integer age);

    List<User> findAllByRole(String role);

    @Query("select user from User as user where user.username=?1 and user.password =?2")
    public User login(String username,String password);

    @Query("select user from User as user where user.id=?1 and user.role ='Admin'")
    public User findAdmin(String id);

    public List<User> findAllByJoiningYearAfter(String date);
    public List<User> findAllByAgeAndJoiningYearGreaterThanEqual(Integer age , String joinYear);
}
