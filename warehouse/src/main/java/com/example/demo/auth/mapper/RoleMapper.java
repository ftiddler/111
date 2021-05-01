package com.example.demo.auth.mapper;

import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface RoleMapper {

    @Select("select r.* from repo_user u, repo_user_role ur, repo_role r where u.account = #{account} and u.id = ur.u_id and ur.r_id = r.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "p_id", property = "positionId"),
            @Result(column = "d_id", property = "departmentId"),
            @Result(column = "role_describe", property = "describe")
    })
    List<Role> selectRoleByAccount(@Param("account") String account);

    @Select("select id from repo_role where role_describe = #{describe}")
    BigInteger selectRoleIdByDescribe(@Param("describe") String describe);

    @Select("select id from department_manage where department_name = #{departmentName}")
    BigInteger selectDepartmentIdByName(@Param("departmentName") String departmentName);

    @Select("select id from repo_position where position_name = #{positionName}")
    BigInteger selectPositionIdByName(@Param("positionName") String positionName);

    @Insert("insert into repo_role (d_id, p_id, role_describe) values (#{departmentId}, #{positionId}, #{describe})")
    int insertRole(@Param("departmentId") BigInteger departmentId, @Param("positionId") BigInteger positionId,
                   @Param("describe") String describe);

}

