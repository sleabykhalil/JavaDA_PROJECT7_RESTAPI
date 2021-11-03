package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "minimum 8 character mandatory")
    @Pattern(regexp = "^(?=\\P{Ll}*\\p{Ll})(?=\\P{Lu}*\\p{Lu})(?=\\P{N}*\\p{N})(?=[\\p{L}\\p{N}]*[^\\p{L}\\p{N}])[\\s\\S]{8,}$"
            , message = " password must contain: capital latter and small latter and digit and special character")
    //@Pattern(regexp = "(?=\\P{Lu}*\\p{Lu})$", message = "minimum one character in Capital latter ")
    //@Pattern(regexp = "^(?=\\P{Ll}*\\p{Ll})", message = "minimum one character in smale latter ")
    //@Pattern(regexp = "^(?=\\P{N}*\\p{N})$", message = "minimum one digit ")
    //@Pattern(regexp = "^(?=[\\p{L}\\p{N}]*[^\\p{L}\\p{N}])$", message = "minimum one special character")
//    @Pattern.List({
//            @Pattern(regexp = "\\B[A-Z]+\\B", message = "minimum one character in Capital latter "),
//            @Pattern(regexp = "\\B[0-9]+\\B", message = "minimum one digit "),
//            @Pattern(regexp = "\\B[&%$#@!~]+\\B", message = "minimum one special character")})
    private String password;

    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
