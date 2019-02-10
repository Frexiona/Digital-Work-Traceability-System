package com.numsource.artproject.dao;

import com.numsource.artproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailAddress(String emailAddress);

}
