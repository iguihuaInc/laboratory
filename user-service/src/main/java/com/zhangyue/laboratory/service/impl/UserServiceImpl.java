package com.zhangyue.laboratory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangyue.laboratory.dto.post.UserPostDto;
import com.zhangyue.laboratory.dto.view.UserViewDto;
import com.zhangyue.laboratory.mapper.UserMapper;
import com.zhangyue.laboratory.model.UserModel;
import com.zhangyue.laboratory.service.UserService;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean create(UserPostDto postDto) {
        UserModel userModel = new UserModel(postDto);
        int i = userMapper.create(userModel);
        return i > 0;
    }

    @Override
    public UserViewDto findUserById(Long id) {
        UserModel userModel = userMapper.findUserById(id);
        return new UserViewDto(userModel);
    }
}
