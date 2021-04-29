package com.example.demo.entity;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Role {
    private BigInteger id;
    private BigInteger positionId;
    private BigInteger departmentId;
    private String describe;
}
