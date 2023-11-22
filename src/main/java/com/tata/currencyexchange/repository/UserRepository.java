package com.tata.currencyexchange.repository;

import com.tata.currencyexchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
