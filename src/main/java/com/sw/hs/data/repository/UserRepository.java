package com.sw.hs.data.repository;


import com.sw.hs.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);   // Optional => null 대비
}
