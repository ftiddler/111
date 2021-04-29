package com.example.demo.systemManagement.mapper;

import com.example.demo.entity.PurchaseInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Minli Hong
 * @date 2021-04-27
 */
@Repository
public interface PurchaseInfoManageMapper {

    @Select("select * from purchase_information")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "approver", property = "approver"),
            @Result(column = "closing_date", property = "closingDate"),
            @Result(column = "auditor", property = "auditor"),
            @Result(column = "prepare", property = "prepare")
    })
    List<PurchaseInfo> selectAll();

    @Insert("insert into purchase_information (approver, closing_date, auditor, prepare) values " +
            "(#{approver}, #{closingDate}, #{auditor}, #{prepare})")
    int insertPurchaseInfo(@Param("approver") String approver, @Param("closingDate") Date closingDate,
                           @Param("auditor") String auditor, @Param("prepare") String prepare);

    @Delete("delete from purchase_information where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Update("update purchase_information set approver = #{approver}, closing_date = #{closingDate}, " +
            "auditor = #{auditor}, prepare = #{prepare} where id = #{id}")
    int updatePurchaseInfo(@Param("approver") String approver, @Param("closingDate") Date closingDate,
                           @Param("auditor") String auditor, @Param("prepare") String prepare, @Param("id") Integer id);

}
