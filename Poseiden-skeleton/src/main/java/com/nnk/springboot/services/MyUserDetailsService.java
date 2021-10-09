package com.nnk.springboot.services;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.domain.dto.MyUserDto;
import com.nnk.springboot.domain.dto.mapper.MyUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserService myUserService;
    @Autowired
    MyUserMapper myUserMapper;

    /**
     * Lode user from DB
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserService.findUserByUsername(username);
        MyUserDto myUserDto = myUserMapper.myUserToMyUserDte(myUser);
        Set<String> roleSet = getUserRoles(myUserDto);
        List<GrantedAuthority> authorities = getUserAuthority(roleSet);
        UserDetails userDetails = buildClientForAuthentication(myUserDto, authorities);
        return userDetails;
    }

    private Set<String> getUserRoles(MyUserDto myUserDto) {
        Set<String> roleSet=new HashSet<>();
        roleSet.add(myUserDto.getRole());
        return roleSet;
    }

    private UserDetails buildClientForAuthentication(MyUserDto myUserDto, List<GrantedAuthority> authorities) {
        User user = new User(myUserDto.getUsername(), myUserDto.getPassword(), authorities);
        return user;
    }

    private List<GrantedAuthority> getUserAuthority(Set<String> myUserRoleSet) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : myUserRoleSet) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }
}
