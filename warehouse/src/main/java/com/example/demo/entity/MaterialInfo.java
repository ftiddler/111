package com.example.demo.entity;

import lombok.Data;

@Data
public class MaterialInfo {
    private Integer id;
    private String materialCode;
    private String materialName;
    private String modelSpec;
    private String measureUnit;
}
