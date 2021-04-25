package com.example.demo.auth.mapper;

import com.example.demo.auth.entity.Identity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdentityMapper {
    /**
     * 根据用户id查询出用户的所有身份
     * @param userId 用户id（账号）
     * @return 用户的身份信息
     */
    @Select("select  B.id id,o_code,r_code ,identity_describe from t_user_identity A left join t_identity B on A.i_id = B.id where A.u_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "ocode", column = "o_code"),
            @Result(property = "rcode", column = "r_code"),
            @Result(property = "describe", column = "identity_describe")
    })
    public List<Identity> findIdentityByUserId(Integer userId);
}
