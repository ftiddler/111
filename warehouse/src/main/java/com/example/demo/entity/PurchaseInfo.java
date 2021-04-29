package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PurchaseInfo {
    private Integer id;
    private String approver;
    private Date closingDate;
    private String auditor;
    private String prepare;
}
