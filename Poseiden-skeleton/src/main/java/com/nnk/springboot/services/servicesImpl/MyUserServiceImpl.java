package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl implements MyUserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public MyUser findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(RuntimeException::new);
    }
}
