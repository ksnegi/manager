package com.negi.manager.dao;

import java.util.List;

import com.negi.manager.model.User;

public interface UserDAO {

    User upsert(User user);

    void remove(String id);

    User getById(String id);

    List<User> getAll();

}
