package com.explore.SpringSecurity.Service;

import com.explore.SpringSecurity.Entity.UserPrincipal;
import com.explore.SpringSecurity.Entity.users;
import com.explore.SpringSecurity.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        users user = repo.findByUsername(username);

        if(user==null){
            System.out.println("User not Found");
            throw new UsernameNotFoundException("User name not found");
        }

        return new UserPrincipal(user);
    }
}
