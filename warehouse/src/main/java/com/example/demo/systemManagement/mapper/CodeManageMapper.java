package com.example.demo.systemManagement.mapper;

import com.example.demo.entity.MaterialInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Minli Hong
 * @date 2021-04-25
 */
@Repository
public interface CodeManageMapper {

    @Repository
    interface Sel {
        @Select("select * from base_information")
        @Results({
                @Result(column = "id", property = "id"),
                @Result(column = "material_code", property = "materialCode"),
                @Result(column = "material_name", property = "materialName"),
                @Result(column = "model_specification", property = "modelSpec"),
                @Result(column = "measure_unit", property = "measureUnit"),
                @Result(column = "material_type", property = "materialType")
        })
        List<MaterialInfo> selectAll();
    }

    @Repository
    interface Ins {
        @Insert("insert into base_information (material_name, model_specification, measure_unit, material_type) " +
                "values (#{materialName}, #{modelSpec}, #{measureUnit}, #{materialType})")
        int insertMaterial(@Param("materialName") String materialName, @Param("modelSpec") String modelSpec,
                           @Param("measureUnit") String modelUnit, @Param("materialType") String materialType);
    }

    @Repository
    interface Del {
        @Delete("delete from base_information where id = #{id}")
        int deleteById(@Param("id") int id);
    }

    @Repository
    interface Upd {
        @Update("update base_information set material_name = #{materialName}, " +
                "model_specification = #{modelSpec}, measure_unit = #{measureUnit}, material_type = #{materialType} where id = #{id}")
        int updateMaterial(@Param("materialName") String materialName, @Param("modelSpec") String modelSpec, @Param("measureUnit") String modelUnit,
                           @Param("materialType") String materialType, @Param("id") Integer id);
    }
}
