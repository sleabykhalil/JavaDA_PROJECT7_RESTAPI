package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.repositories.MyUserRepository;
import com.nnk.springboot.services.MyUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public MyUser add(MyUser user) {
        MyUser myUserToAdd = myUserRepository.save(user);
        log.debug("User added id=[{}] ", user.getId());
        return myUserToAdd;
    }

    @Override
    public MyUser findById(Integer id) {
        Optional<MyUser> myUser = myUserRepository.findById(id);
        if (myUser.isPresent()) {
            log.debug("User found id=[{}]", id);
            return myUser.get();
        }
        log.debug("User not found id=[{}]", id);
        return null;
    }

    @Override
    public MyUser update(MyUser user) {
        MyUser userToUpdate = myUserRepository.save(user);
        log.debug("User updated id=[{}]", userToUpdate.getId());
        return userToUpdate;
    }

    @Override
    public void delete(Integer id) {
        if (myUserRepository.findById(id).isPresent()) {
            myUserRepository.deleteById(id);
            log.debug("User deleted id=[{}]", id);
        } else {
            log.debug("User not found =[{}]", id);
        }
    }
}

