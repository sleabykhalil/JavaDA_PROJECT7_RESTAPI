package com.nnk.springboot.services;

import com.nnk.springboot.domain.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private MyUserService myUserService;

    public MyUserDetailsService(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

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

    public String getUserInfo(Principal user) {
        StringBuffer userInfo = new StringBuffer();
        if (user instanceof UsernamePasswordAuthenticationToken) {
            userInfo.append(getUserNamePasswordLoginInfo(user));
        } else if (user instanceof OAuth2AuthenticationToken) {
            userInfo.append(getOauth2LoginInfo(user));
        }
        return userInfo.toString();
    }

    private StringBuffer getUserNamePasswordLoginInfo(Principal user) {
        StringBuffer usernameInfo = new StringBuffer();
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) user;
        if (token.isAuthenticated()) {
            User u = (User) token.getPrincipal();
            usernameInfo.append(u.getUsername());
        } else {
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }

    private StringBuffer getOauth2LoginInfo(Principal user) {
        StringBuffer protectedInfo = new StringBuffer();
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) user;

        if (authToken.isAuthenticated()) {
            //Map<String , Object> userAttributes = authToken.getPrincipal().getAttributes();
            Map<String, Object> userAttributes = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();
            protectedInfo.append(userAttributes.get("login"));
        } else {
            protectedInfo.append("NA");
        }

        return protectedInfo;
    }

}
