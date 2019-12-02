package com.zhangyue.laboratory.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import com.zhangyue.laboratory.dto.post.UserPostDto;
import com.zhangyue.laboratory.service.UserService;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUserTest() {
        UserPostDto postDto = new UserPostDto();
        postDto.setName("zhangyong");
        postDto.setBirthday(new Date());
        postDto.setCreateTime(new Date());
        postDto.setUpdateTime(new Date());
        userService.create(postDto);
    }

    @Test
    public void findUserByIdTest() {
        userService.findUserById(1l);
    }
}
