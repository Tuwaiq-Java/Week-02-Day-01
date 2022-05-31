package com.example.usersmanagementsoftware.service;


import com.example.usersmanagementsoftware.model.User;
import com.example.usersmanagementsoftware.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List getAllUser() {
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }
    public User getUserById(String userId) {
        return userRepository.findById(userId).get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getGreaterThanAge(Integer age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    public Integer getCountByRole(String role) {
        return userRepository.findAllByRole(role).size();
    }

    public String logIn(String username,String password) {
        if(userRepository.login(username,password)==null){
            return "there is no user with this information";
        }

        return "Login success";
    }

    public User updateUser(String id , User user){
        User admin = userRepository.findAdmin(id);
       if (admin!=null){
           admin =new User(user.getId(),user.getUsername(),
                   user.getPassword(), user.getEmail(),user.getRole(),user.getJoiningYear(),user.getAge());
           userRepository.save(admin);
           return admin;
       }
       return null;
    }

    public User updatePassword(String id , String password){
            User currentUser = userRepository.findById(id).get();
            if (currentUser!=null){
                currentUser.setPassword(password);
                userRepository.save(currentUser);
                return currentUser;
            }
            return null;
    }

    public String checkJoinYear(String id , String joinYear){
        User currentUser = userRepository.findById(id).get();
        if (currentUser!=null&&currentUser.getJoiningYear().equalsIgnoreCase(joinYear)){
           return "Yes the join year is the same";
        }
        return "No the join year is not the same";
    }

    public List<User> getByJoinDate(String joinYear){
        return userRepository.findAllByJoiningYearAfter(joinYear);
    }

    public List<User> getByAgeAndJoinYear(Integer age , String joinYear){
            return userRepository.findAllByAgeAndJoiningYearGreaterThanEqual(age,joinYear);
    }

}
