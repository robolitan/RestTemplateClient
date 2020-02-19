package com.springboot.resttemplate.services;

import com.springboot.resttemplate.controllers.RESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class UserDatailServiceImpl implements UserDetailsService {

    @Autowired
    RestTemplateService restTemplateService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = restTemplateService.getUserByLogin(s);
        return userDetails;
    }
}
