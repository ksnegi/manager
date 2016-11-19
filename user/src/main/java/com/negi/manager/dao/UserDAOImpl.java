package com.negi.manager.dao;

import com.negi.manager.config.ManagerCassandraTemplate;
import com.negi.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public abstract class UserDAOImpl implements UserDAO {

    @Autowired
    private ManagerCassandraTemplate cassandraTemplate;

    @Override
    public User create(User user) {
        UUID id = UUID.randomUUID();
        user.setId(id);
        return cassandraTemplate.create(user);
    }

    @Override
    public User update(User user) {
        return cassandraTemplate.update(user);
    }

    @Override
    public void delete(UUID id) {
        cassandraTemplate.deleteById(id, User.class);
    }

    @Override
    public User findById(UUID id) {
        return cassandraTemplate.findById(id, User.class);
    }

    @Override
    public List<User> findAll() {
        return cassandraTemplate.findAll(User.class);
    }

}
