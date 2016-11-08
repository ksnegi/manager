package com.negi.manager.dao.cassandra;

import java.util.List;

import com.negi.manager.model.User;
import com.negi.manager.dao.UserDAO;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    public User upsert(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public User getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
