package com.nnk.springboot.services;

import com.nnk.springboot.domain.MyUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyUserService {

    MyUser findUserByUsername(String username);

    List<MyUser> findAll();

    MyUser add(MyUser user);

    MyUser findById(Integer id);

    MyUser update(MyUser user);

    void delete(Integer id);
}
