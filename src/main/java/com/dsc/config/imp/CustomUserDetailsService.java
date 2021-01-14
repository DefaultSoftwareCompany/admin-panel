package com.dsc.config.imp;

import com.dsc.model.User;
import com.dsc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getByUserName(username);
        if (user == null) throw new UsernameNotFoundException("There is no user with such an id");
        return new CustomUserDetails(user);
    }
}
