package com.example.demo.systemManagement.mapper;

import com.example.demo.entity.Supplier;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Minli Hong
 * @date 2021-04-26
 */
@Repository
public interface ProviderManageMapper {

    @Select("select * from service_provider")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "registration_number", property = "regisNumber"),
            @Result(column = "supplier_name", property = "supName"),
            @Result(column = "supplier_type", property = "supType"),
            @Result(column = "address_postcode", property = "addressPostcode"),
            @Result(column = "contacts", property = "contacts"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "remark", property = "remark")
    })
    List<Supplier> selectAll();

    @Insert("insert into service_provider (registration_number, supplier_name, supplier_type, " +
            "address_postcode, contacts, phone, remark) values (#{regisNumber}, #{supName}, #{supType}, " +
            "#{addressPostcode}, #{contacts}, #{phone}, #{remark})")
    int insertSupplier(@Param("regisNumber") String regisNumber, @Param("supName") String supName,
                       @Param("supType") Boolean supType, @Param("addressPostcode") String addressPostcode,
                       @Param("contacts") String contacts, @Param("phone") String phone, @Param("remark") String remark);

    @Delete("delete from service_provider where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Update("update service_provider set registration_number = #{regisNumber}, supplier_name = #{supName}, " +
            "supplier_type = #{supType}, address_postcode = #{addressPostcode}, contacts = #{contacts}, " +
            "phone = #{phone}, remark = #{remark} where id = #{id}")
    int updateSupplier(@Param("regisNumber") String regisNumber, @Param("supName") String supName,
                       @Param("supType") Boolean supType, @Param("addressPostcode") String addressPostcode,
                       @Param("contacts") String contacts, @Param("phone") String phone,
                       @Param("remark") String remark, @Param("id") Integer id);

}
