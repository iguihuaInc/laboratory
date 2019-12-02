package com.zhangyue.laboratory.model;

import java.util.Date;

import com.zhangyue.laboratory.dto.post.UserPostDto;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
public class UserModel {
    private Long id;
    private String name;
    private Date birthday;
    private Date createTime;
    private Date updateTime;

    public UserModel() {
    }

    public UserModel(UserPostDto postDto) {
        this.name = postDto.getName();
        this.birthday = postDto.getBirthday();
        this.createTime = postDto.getCreateTime();
        this.updateTime = postDto.getUpdateTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
