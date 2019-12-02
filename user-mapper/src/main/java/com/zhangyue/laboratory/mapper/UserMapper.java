package com.zhangyue.laboratory.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zhangyue.laboratory.model.UserModel;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
public interface UserMapper {

    @Insert("insert into user(name,birthday,create_time,update_time) values (#{name},#{birthday},#{createTime},#{updateTime})")
    int create(UserModel userModel);

    @Select("select * from user where id = #{id}")
    UserModel findUserById(Long id);
}
