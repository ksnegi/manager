package com.negi.manager.dao;

import com.negi.manager.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDAO {

    User create(User user);

    User update(User user);

    void delete(UUID id);

    User findById(UUID id);

    List<User> findAll();

}
