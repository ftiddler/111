package com.example.demo.auth.mapper;

import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    @Select("select r.* from repo_user u, repo_user_role ur, repo_role r where u.account = #{account} and u.id = ur.u_id and ur.r_id = r.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "p_id", property = "positionId"),
            @Result(column = "d_id", property = "departmentId"),
            @Result(column = "describe", property = "describe")
    })
    List<Role> selectRoleByAccount(@Param("account") String account);

}

