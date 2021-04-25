package com.example.demo.auth.mapper;
import com.example.demo.auth.entity.LoginInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author GuoWei
 * @date 2021-3-16
 * <p>
 *     用户账号密码角色权限的相关查询
 * </p>
 */
@Repository
public interface AuthUserMapper {
    /**
     *
     * @param account 用户账号
     * @return 用户账号 密码 信息
     */
    @Select("select id, account,username,password from t_user where account = #{account}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "account", property = "account"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password")
    })
    LoginInfo findUserByAccount(String account);

}
