package com.example.usersmanagement.service;

import com.example.usersmanagement.modle.UsersManagement;
import com.example.usersmanagement.repository.UseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UseRepository useRepository;


    public List<UsersManagement> getUsers() {
        return useRepository.findAll();
    }

    public void addUses(UsersManagement uses) {
        useRepository.save(uses);
    }

    public UsersManagement getUsers(Integer id) {
        return useRepository.findById(id).get();
    }


    public UsersManagement getUsersByEmail(String email) {
        return useRepository.findByEmail(email);
    }


    public List<UsersManagement> getUsersByAge(Integer age) {
        return useRepository.findAllByAgeGreaterThan(age);
    }

    public Integer getCountUsersByRole(String role) {
        return useRepository.countAllByRole(role);
    }

    public UsersManagement getUsernameAndPassword(String username, String password) {
        return useRepository.findByUsernameEqualsAndPasswordEquals(username,password);
    }


    public void updateuser(UsersManagement newusers, Integer id) {
            UsersManagement oldusers=useRepository.findById(id).get();
            oldusers.setEmail(newusers.getEmail());
            oldusers.setUsername(newusers.getUsername());
            oldusers.setPassword(newusers.getPassword());
            oldusers.setAge(newusers.getAge());
            oldusers.setRole(newusers.getRole());
            oldusers.setJoiningYear(newusers.getJoiningYear());
            useRepository.save(oldusers);

        }

    public void updatePassword(String newPassword, Integer id) {
        UsersManagement oldusers=useRepository.findById(id).get();
        oldusers.setPassword(newPassword);
        useRepository.save(oldusers);

    }

    public UsersManagement getUserByIdAndYear(Integer id, Integer year) {
        return useRepository.findByIdAndJoiningYearEquals(id,year);
    }

    public List<UsersManagement> getUsersByYear(Integer year) {
        return useRepository.findAllByJoiningYearGreaterThanEqual(year);
    }

    public List<UsersManagement> getUserByAgeAndYearEqualsOrBefore(Integer age, Integer year) {
       return useRepository.findUserByAgeEqualsAndYearBefore(age, year);
    }
}


