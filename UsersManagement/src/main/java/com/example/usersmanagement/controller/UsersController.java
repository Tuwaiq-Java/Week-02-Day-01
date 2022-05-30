package com.example.usersmanagement.controller;

import com.example.usersmanagement.modle.UsersManagement;
import com.example.usersmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UsersController {

    private final UserService userService;

//Get all users
    @GetMapping
    public ResponseEntity<List<UsersManagement>> getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }
//Add new users
    @PostMapping
    public ResponseEntity<?> addUsers(@RequestBody @Valid UsersManagement users, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUses(users);
        return ResponseEntity.status(200).body(" user added !");
    }
//Create endpoint that takes users id and return the user with this id

    @GetMapping("/{id}")
    public ResponseEntity<UsersManagement> getUses(@PathVariable Integer id) {

        return ResponseEntity.status(200).body(userService.getUsers(id));
    }
//Create endpoint that takes email and return the user with this email

    @GetMapping("/email")
    public ResponseEntity<UsersManagement> getStudentByName(@RequestParam String email){
        return ResponseEntity.status(200).body(userService.getUsersByEmail(email));
    }
//Create endpoint that takes age and return the user with older than this age
    @GetMapping("/age")
    public ResponseEntity<List<UsersManagement>> getUsersByAge(@RequestParam Integer age){
        return ResponseEntity.status(200).body(userService.getUsersByAge(age));
    }
//Create endpoint that takes role and return the total count having this role
    @GetMapping("/role")
    public ResponseEntity getCountUsersByRole(@RequestParam String role){
         return ResponseEntity.status(200).body(userService.getCountUsersByRole(role));
    }
//Create endpoint that takes username and password and check if it's correct or not
    @GetMapping("/username-password")
     public ResponseEntity<UsersManagement> getUserByUsernameAndPassword(@RequestParam String username,@RequestParam String password){
        return ResponseEntity.status(200).body(userService.getUsernameAndPassword(username,password));
     }
//Create endpoint that takes userid and user object , update the olduser object with the new object after verifying the userid belong to admin user
    @PutMapping("/newuser/{id}")
     public ResponseEntity updateUser(@RequestBody UsersManagement users ,@PathVariable Integer id,Errors errors){
        if(errors.hasErrors()){
             return ResponseEntity.status(200).body(errors.getFieldError().getDefaultMessage());
        }
         userService.updateuser(users,id);
         return ResponseEntity.status(HttpStatus.OK).body("user Updated!");
     }
//Create endpoint that takes newPassword and userid , update the olduser password with the new Password
     @PutMapping("/newpassword/{id}")
     public ResponseEntity updatePassword(@RequestBody String password ,@PathVariable Integer id,Errors errors){
        if(errors.hasErrors()){
        return ResponseEntity.status(200).body(errors.getFieldError().getDefaultMessage());
    }
        userService.updatePassword(password,id);
        return ResponseEntity.status(HttpStatus.OK).body("password Updated!");
    }
//Create endoiunt that takes joiningYear and userid , and return if this user joined with the date that being sent or not
    @GetMapping ("/joining")
    public ResponseEntity<UsersManagement> getUserByIdAndYear(@RequestParam Integer id,@RequestParam Integer year){
      return ResponseEntity.status(200).body(userService.getUserByIdAndYear(id,year));
    }
//Create endoiunt that takes joiningYear and return all the users who joined in this date or after
    @GetMapping("/Year")
    public ResponseEntity<List<UsersManagement>> getUsersByYear(@RequestParam Integer year){
       return ResponseEntity.status(200).body(userService.getUsersByYear(year));
    }
//Create endoiunt that takes age and joiningYear and return all the users who joined in this date or before and all have the same age

    @GetMapping("/ageYear")
    public ResponseEntity<List<UsersManagement>> getUsersByAgeYear(@RequestParam Integer age,@RequestParam Integer year){
        return ResponseEntity.status(200).body(userService.getUserByAgeAndYearEqualsOrBefore(age,year));
    }


}
