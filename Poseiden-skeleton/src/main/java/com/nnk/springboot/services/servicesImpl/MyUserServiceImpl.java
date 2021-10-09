package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.services.MyUserService;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl implements MyUserService {
    @Override
    public MyUser findUserByUsername(String username) {
        return null;
    }
}
