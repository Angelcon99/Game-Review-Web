package com.sw.hs.service;

import com.sw.hs.data.entity.User;

public interface UserService {
    public User userSave(User user);

    public boolean loginCheck(User user);
}
