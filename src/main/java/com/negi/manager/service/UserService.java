package com.negi.manager.service;

import java.util.List;

import com.negi.manager.model.User;

public interface UserService {

    User upsert(User user);

    void remove(String id);

    User getById(String id);

    List<User> getAll();

}
