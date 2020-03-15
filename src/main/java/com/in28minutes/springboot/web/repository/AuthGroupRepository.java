package com.in28minutes.springboot.web.repository;

import com.in28minutes.springboot.web.entity.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthGroupRepository extends JpaRepository<AuthGroup, Integer> {
}