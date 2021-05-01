package com.example.demo.systemManagement.mapper;

import com.example.demo.entity.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Minli Hong
 * @date 2021-04-30
 */
@Repository
public interface DepartmentManageMapper {

    @Select("select * from department_manage")
    @Result(column = "department_name", property = "departmentName")
    List<Department> selectAll();

    @Insert("insert into department_manage (department_name, principal) values (#{departmentName}, " +
            "#{principal})")
    int insertDepartment(@Param("departmentName") String departmentName, @Param("principal") String principal);

    @Delete("delete from department_manage where id = #{id}")
    int deleteById(@Param("id") BigInteger id);

    @Update("update department_manage set department_name = #{departmentName}, principal = #{principal} " +
            "where id = #{id}")
    int updateDepartment(@Param("departmentName") String departmentName, @Param("principal") String principal, @Param("id") BigInteger id);

}
