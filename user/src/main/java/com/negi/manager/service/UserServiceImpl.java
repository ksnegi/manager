package com.negi.manager.service;

import com.negi.manager.dao.UserDAO;
import com.negi.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public User create(User user) {
        return userDAO.create(user);
    }

    @Override
    public User update(User user) {
        return userDAO.update(user);
    }

    @Override
    public void delete(String id) {
        userDAO.delete(UUID.fromString(id));
    }

    @Override
    public User findById(String id) {
        return userDAO.findById(UUID.fromString(id));
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

}
