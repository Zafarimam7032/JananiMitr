package com.janani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janani.entity.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer>{

}
