package com.nnk.springboot.services;

import com.nnk.springboot.domain.MyUser;
import org.springframework.stereotype.Service;

@Service
public interface MyUserService {

    MyUser findUserByUsername(String username);
}
