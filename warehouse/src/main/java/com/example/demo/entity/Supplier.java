package com.example.demo.entity;

import lombok.Data;

@Data
public class Supplier {
    private Integer id;
    private String regisNumber;
    private String supName;
    private Boolean supType;
    private String addressPostcode;
    private String contacts;
    private String phone;
    private String remark;
}
