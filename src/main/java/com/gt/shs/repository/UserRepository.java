package com.gt.shs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gt.shs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
