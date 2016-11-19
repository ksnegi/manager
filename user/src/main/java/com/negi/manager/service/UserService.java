package com.negi.manager.service;

import com.negi.manager.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user);

    void delete(String id);

    User findById(String id);

    List<User> findAll();

}
