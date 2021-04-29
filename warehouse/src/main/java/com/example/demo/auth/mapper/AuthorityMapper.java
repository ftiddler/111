package com.example.demo.auth.mapper;

import com.example.demo.entity.Authority;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AuthorityMapper {

    @Select("select a.id from repo_role r, repo_position_authority pa, repo_authority a where r.id = #{id}" +
            " and r.p_id= pa.p_id and pa.a_id = a.id  ")
    List<BigInteger> selectAuthorityIdByRoleId(@Param("id") BigInteger id);

}
