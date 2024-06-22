package com.ecommerce.user.service;

import com.ecommerce.user.model.User;

public interface UserService {
    User register(User user);
      User findByEmail(String email); // Add method to find user by email

    // User findByUsername(String username);
}
