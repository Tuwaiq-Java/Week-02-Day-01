package com.example.usersmanagementsoftware.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    private String password;

    @Column(unique = true)
    private String email;

    @NotEmpty
    @Pattern(regexp = "(Admin|User)")
    private String role;

    @NotEmpty
    private String joiningYear;

    @NotNull
    private Integer age;



}
