package com.example.usersmanagement.modle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@AllArgsConstructor @NoArgsConstructor @Data @Entity
public class UsersManagement {
    @Id
    private Integer id;
    @Column(name = "user_name",unique = true,nullable = false)
    @NotEmpty(message = "userName is required !")
    private String username;
    private String password;
    private String email;
    @Pattern(regexp = "(Admin|Customer)")
    private String role;
    private Integer joiningYear;
    private Integer age;
}
