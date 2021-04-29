package com.example.demo.auth.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from repo_user where account = #{account}")
    User findByAccount(@Param("account") String account);

    @Select("select * from repo_user")
    List<User> selectAll();

    @Insert("insert into repo_user (username, password, account) " +
            "values (#{username}, #{password}, #{account})")
    int insertUser(@Param("username") String username, @Param("password") String password, @Param("account") String account);

    @Delete("delete from repo_user where account = #{account}")
    int deleteByAccount(@Param("account") String account);

    @Update("update repo_user set password = #{password} where account = #{account}")
    int changePasswordByAccount(@Param("password") String password, @Param("account") String account);
}
