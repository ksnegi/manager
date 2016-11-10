package com.negi.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.negi.manager.model.User;
import com.negi.manager.dao.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public User upsert(User user) {
        return userDAO.upsert(user);
    }

    @Override
    public void remove(String id) {
        userDAO.remove(id);
    }

    @Override
    public User getById(String id) {
        return userDAO.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

}
