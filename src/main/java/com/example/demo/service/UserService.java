package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserJpaRepository userJpaRepository;

    @Autowired
    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public User getUser(long id) {
        return userJpaRepository.findById(id).orElse(new User());
    }


    public void saveUser(User user) {
        userJpaRepository.save(user);
    }

    public int getCount() {
        return userJpaRepository.findAll().size();
    }

    public int getMinUserId() {
        var user = userJpaRepository.findAll().stream()
                .findFirst()
                .get();
        return (int) user.getId();
    }
}
