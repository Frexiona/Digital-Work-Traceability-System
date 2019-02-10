package com.numsource.artproject.service;

import com.numsource.artproject.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    public User addUser(User user);
    public User getCurrentUser(String sessionID, HttpServletRequest request, HttpServletResponse response);

}
