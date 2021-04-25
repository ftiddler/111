package com.example.demo.auth.mapper;

import com.example.demo.auth.entity.Authority;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityMapper {
    /**
     * 根据用户身份id获取所有的权限信息
     * @param i_id Identity_id
     * @return 权限信息
     */
    @Select("select  A.authority_id id ,authority_describe descb from t_identity_authority A left join t_authority B on A.authority_id = B.authority_id where A.identity_id = #{i_id}")
    @Results({
            @Result(property = "authorityId",column = "id"),
            @Result(property = "authorityDesc",column = "descb")
    })
    List<Authority> findByIdentityId(String i_id);

    /**
     * 查询出所有的权限id
     * @param i_id
     * @return
     */
    @Select("select distinct authority_id from t_identity_authority where identity_id = #{i_id} ")
    List<String> findAuthorityIdByIdentityId(String i_id);
}
