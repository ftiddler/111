package com.example.demo.auth.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from repo_user where account = #{account}")
    User findByAccount(@Param("account") String account);

    @Insert("insert into repo_user (username, password, authority, account) " +
            "values (#{username}, #{password}, #{authority}, #{account})")
    int insertUser(@Param("username") String username, @Param("password") String password,
                   @Param("authority") String authority, @Param("account") String account);

    @Delete("delete from repo_user where account = #{account}")
    int DeleteByAccount(@Param("account") String account);

    @Update("update repo_user set username = #{username}, password = #{password}, authority = #{authority} where account = #{account}")
    int UpdateByAccount(@Param("username") String username, @Param("password") String password,
                        @Param("authority") String authority, @Param("account") String account);
}
