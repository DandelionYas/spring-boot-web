package com.in28minutes.springboot.web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_FK")
    private List<AuthGroup> authGroups;

    public User(String username, String password, List<AuthGroup> authGroups) {
        this.username = username;
        this.password = password;
        this.authGroups = authGroups;
    }
}