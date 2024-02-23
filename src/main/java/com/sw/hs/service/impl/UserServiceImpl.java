package com.sw.hs.service.impl;

import com.sw.hs.data.entity.User;
import com.sw.hs.data.repository.UserRepository;
import com.sw.hs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service   // 객체(userService)가 생성되어 Springboot Container 에 저장
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    // 회워가입
    @Override
    public User userSave(User user){
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if(u.isPresent())   // 중복된 이메일
            return null;
        else
            return userRepository.save(user);
    }

    // 로그인 체크
    @Override
    public boolean loginCheck(User user){
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if(u.isPresent()){   // Optional.isPresent() => 존재하면
            User uu = u.get();   // Optional 은 직접 쓸 수 없어서 꺼내서 씀
            if(uu.getPassword().equals( user.getPassword()))    // Java 에서는 == 보다 .equals() 를 많이 씀
                return true;
        }

        return false;
    }
}
