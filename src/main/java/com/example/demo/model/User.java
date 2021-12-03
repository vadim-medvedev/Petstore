package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank @NotNull
    private String username;
    @NotBlank @NotNull
    private String firstName;
    @NotBlank @NotNull
    private String lastName;
    @NotBlank @NotNull @Pattern(regexp = "\\b[a-zA-Z0-9._%-]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}\\b")
    private String email;
    @NotBlank @NotNull @Pattern(regexp = ".{8,20}")
    private String password;
    @NotBlank @NotNull @Pattern(regexp = "^\\+375 \\((17|29|33|44)\\) [0-9]{3}-[0-9]{2}-[0-9]{2}$")
    private String phone;
}
