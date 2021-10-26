package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.repositories.MyUserRepository;
import com.nnk.springboot.services.MyUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MyUserServiceImpl implements MyUserService {
    @Autowired
    MyUserRepository myUserRepository;

    @Override
    public MyUser findUserByUsername(String username) {
        MyUser myUser = myUserRepository.findByUsername(username);
        log.info("user found =[{}]", myUser.getUsername());
        return myUser;
    }

    @Override
    public List<MyUser> findAll() {
        return myUserRepository.findAll();
    }
}

