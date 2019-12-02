package com.zhangyue.laboratory.dto.view;

import java.util.Date;

import com.zhangyue.laboratory.model.UserModel;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
public class UserViewDto {
    private Long id;
    private String name;
    private Date birthday;
    private Date createTime;
    private Date updateTime;

    public UserViewDto() {
    }

    public UserViewDto(UserModel userModel) {
        if (userModel != null) {
            this.id = userModel.getId();
            this.name = userModel.getName();
            this.birthday = userModel.getBirthday();
            this.createTime = userModel.getCreateTime();
            this.updateTime = userModel.getUpdateTime();
        }
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
