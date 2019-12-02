package com.zhangyue.laboratory.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.zhangyue.laboratory.dto.view.UserViewDto;
import com.zhangyue.laboratory.service.UserService;
import com.zhangyue.laboratory.dto.post.UserPostDto;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void create(@RequestBody @Valid UserPostDto postDto) {
        userService.create(postDto);
    }

    @GetMapping("/{id}")
    public UserViewDto findUserById(@PathVariable(value = "id") Long id) {
        return userService.findUserById(id);
    }
}
