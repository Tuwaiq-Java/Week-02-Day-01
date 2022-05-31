package com.example.usersmanagementsoftware.controller;


import com.example.usersmanagementsoftware.model.User;
import com.example.usersmanagementsoftware.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public ResponseEntity getUser(){
        return ResponseEntity.status(200).body(userService.getAllUser());
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody @Valid User user , Errors erros){

        if (erros.hasErrors()){
            return ResponseEntity.status(400).body(erros.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User Added!!");
    }

    @GetMapping("id/{userid}")
    public ResponseEntity getUserById(@PathVariable String userid){
        return ResponseEntity.status(200).body(userService.getUserById(userid));
    }

    @GetMapping("email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    @GetMapping("age/{age}")
    public ResponseEntity getUserByAge(@PathVariable Integer age){
        return ResponseEntity.status(200).body(userService.getGreaterThanAge(age));
    }

    @GetMapping("role/{role}")
    public ResponseEntity getUserByRole(@PathVariable String role){
        return ResponseEntity.status(200).body(userService.getCountByRole(role));
    }


    @GetMapping("login/{userid}/{password}")
    public ResponseEntity logIn(@PathVariable String username,@PathVariable String password){
        return ResponseEntity.status(200).body(userService.logIn(username,password));
    }

    @PutMapping("update-user/{userid}")
    public ResponseEntity updateUser(@PathVariable String userid,@RequestBody User user){
        return ResponseEntity.status(200).body(userService.updateUser(userid,user));
    }

    @PutMapping("reset-password/{userid}")
    public ResponseEntity resetPassword(@RequestBody String password , @PathVariable String userid){
        return ResponseEntity.status(200).body(userService.updatePassword(userid,password));
    }

    @GetMapping("check-date/{userid}")
    public ResponseEntity checkDate(@PathVariable String userid , @RequestBody String joinDate){
        return ResponseEntity.status(200).body(userService.checkJoinYear(userid,joinDate));
    }

    @GetMapping("count-date")
    public ResponseEntity checkDate(@RequestBody String joinDate){
        return ResponseEntity.status(200).body(userService.getByJoinDate(joinDate));
    }

    @GetMapping("count-date/{age}")
    public ResponseEntity checkDateAndAge(@PathVariable Integer age ,@RequestBody String joinDate){
        return ResponseEntity.status(200).body(userService.getByAgeAndJoinYear(age,joinDate));
    }

}
