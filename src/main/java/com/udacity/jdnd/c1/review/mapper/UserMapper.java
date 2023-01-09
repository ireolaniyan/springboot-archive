package com.udacity.jdnd.c1.review.mapper;

import com.udacity.jdnd.c1.review.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (username, firstName, lastName, password, salt) VALUES (#{username}, #{firstName}, #{lastName}, #{password}, #{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer addUser(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{userId}")
    void deleteUser(Integer userId);
}
