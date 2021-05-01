package com.example.demo.entity;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Department {
    private BigInteger id;
    private String departmentName;
    private String principal;
}
