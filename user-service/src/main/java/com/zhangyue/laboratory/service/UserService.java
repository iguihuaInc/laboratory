package com.zhangyue.laboratory.service;

import com.zhangyue.laboratory.dto.post.UserPostDto;
import com.zhangyue.laboratory.dto.view.UserViewDto;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
public interface UserService {

    boolean create(UserPostDto postDto);

    UserViewDto findUserById(Long id);
}
