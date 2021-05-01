package com.example.demo.auth.mapper;

import com.example.demo.auth.entity.UserTable;
import com.example.demo.entity.Department;
import com.example.demo.entity.Position;
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
    List<UserTable> selectAll();

    @Select("select * from repo_position")
    @Result(column = "position_name", property = "positionName")
    List<Position> selectAllPositions();

    @Select("select * from department_manage")
    @Result(column = "department_name", property = "departmentName")
    List<Department> selectAllDepartments();

    @Insert("insert into repo_user (username, password, account) " +
            "values (#{username}, #{password}, #{account})")
    int insertUser(@Param("username") String username, @Param("password") String password, @Param("account") String account);

    @Insert("insert into repo_user_role (u_id, r_id) values (#{userId}, #{roleId})")
    int insertUserRole(@Param("userId") BigInteger userId, @Param("roleId") BigInteger roleId);

    @Delete("delete from repo_user_role where u_id = #{userId} and r_id = (select id from repo_role where role_describe = #{describe})")
    int deleteUserRole(@Param("userId") BigInteger userId, @Param("describe") String describe);

    @Delete("delete from repo_user where account = #{account}")
    int deleteByAccount(@Param("account") String account);

    @Update("update repo_user set password = #{password} where account = #{account}")
    int changePasswordByAccount(@Param("password") String password, @Param("account") String account);
}
