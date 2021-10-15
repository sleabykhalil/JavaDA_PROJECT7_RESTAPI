package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer>, JpaSpecificationExecutor<MyUser> {
    MyUser findByUsername(String username);
}
