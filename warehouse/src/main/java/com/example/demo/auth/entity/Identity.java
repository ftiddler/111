package com.example.demo.auth.entity;

import lombok.Data;

@Data
public class Identity {
    private String id;
    private String ocode;
    private String rcode;
    private String describe;
}
