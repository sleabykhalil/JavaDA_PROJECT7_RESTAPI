package com.nnk.springboot.services;

import com.nnk.springboot.domain.MyUser;
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
        Set<String> roleSet = getUserRoles(myUser);
        List<GrantedAuthority> authorities = getUserAuthority(roleSet);
        UserDetails userDetails = buildClientForAuthentication(myUser, authorities);
        return userDetails;
    }

    private Set<String> getUserRoles(MyUser myUser) {
        Set<String> roleSet = new HashSet<>();
        roleSet.add(myUser.getRole());
        return roleSet;
    }

    private UserDetails buildClientForAuthentication(MyUser myUser, List<GrantedAuthority> authorities) {
        User user = new User(myUser.getUsername(), myUser.getPassword(), authorities);
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
