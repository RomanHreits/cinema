package com.cinema.service.impl;

import com.cinema.dao.UserDao;
import com.cinema.model.User;
import com.cinema.service.UserService;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @Override
    public User add(User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User get(Long userId) {
        return userDao.get(userId);
    }
}
