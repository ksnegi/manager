package com.negi.manager.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.negi.manager.model.User;
import com.negi.manager.service.UserService;

@RestController
public class ManagementResource {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    User create(@RequestBody User user) {
        return userService.upsert(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id) {
        userService.remove(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User getById(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    User update(@RequestBody User user) {
        return userService.upsert(user);
    }

}
