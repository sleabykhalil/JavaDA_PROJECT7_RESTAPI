package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer>, JpaSpecificationExecutor<MyUser> {
Optional<MyUser> findUserByUsername(String username);
}
