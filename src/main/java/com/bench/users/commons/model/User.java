package com.bench.users.commons.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, message = "Username should have at least 5 characters")
    @Column(unique = true, name = "username")
    private String username;

    @Size(min = 5, message = "Password should have at least 5 characters")
    @Column(name = "password")
    private String password;

    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "Email cannot be empty")
    @Column(name = "email")
    private String email;

    @Size(min = 7, max = 8, message = "Dni must have 7 to 8 characters")
    @Column(name = "dni")
    private String dni;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
    private List<Role> roles = new ArrayList<>();

    @Transient
    private Integer port;

}
