package com.teleapps.security.service;

import com.teleapps.security.entity.UserInfo;
import com.teleapps.security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo>userInfo =  repository.findByName(username);
     return userInfo.map(UserInfoUserDetails::new)
                 .orElseThrow(()-> new UsernameNotFoundException("user not found "+ username));


    }
}
