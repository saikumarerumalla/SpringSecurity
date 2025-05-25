package com.explore.SpringSecurity.Repo;

import com.explore.SpringSecurity.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<users, Integer> {
    users findByUsername(String username);
}
