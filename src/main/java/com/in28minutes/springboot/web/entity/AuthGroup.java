package com.in28minutes.springboot.web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "AUTH_USER_GROUP")
@Data
@NoArgsConstructor
public class AuthGroup {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "AUTH_GROUP")
    private String authGroup;

    public AuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }
}
