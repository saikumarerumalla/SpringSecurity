package com.explore.SpringSecurity.Service;

import com.explore.SpringSecurity.Entity.users;
import com.explore.SpringSecurity.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public users registerUser(users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

}
