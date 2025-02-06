package com.janani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janani.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
