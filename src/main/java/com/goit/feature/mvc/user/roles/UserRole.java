package com.goit.feature.mvc.user.roles;

import com.goit.feature.mvc.user.User;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Set;

@Data
//@Entity
//@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EUserRole name;

    @OneToMany(mappedBy="role")
    private Set<User> users;
}
