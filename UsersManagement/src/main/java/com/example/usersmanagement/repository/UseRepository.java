package com.example.usersmanagement.repository;

import com.example.usersmanagement.modle.UsersManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UseRepository extends JpaRepository<UsersManagement,Integer> {
    UsersManagement findByEmail(String email);
    List<UsersManagement> findAllByAgeGreaterThan(Integer age);
    List<UsersManagement> findAllByJoiningYearGreaterThanEqual(Integer joiningyear);

    @Query("SELECT u FROM UsersManagement u WHERE u.age=?1 and u.joiningYear<=?2 ")
    List<UsersManagement> findUserByAgeEqualsAndYearBefore(Integer age,Integer joiningyear);

    Integer countAllByRole(String role);

    @Query("SELECT u FROM UsersManagement u WHERE u.id=?1 and u.joiningYear=?2 ")
    UsersManagement findByIdAndJoiningYearEquals(Integer id, Integer joiningYear);

    @Query("SELECT u FROM UsersManagement u WHERE u.username=?1 and u.password=?2 ")
    UsersManagement findByUsernameEqualsAndPasswordEquals(String username,String password);

}
