package com.example.demo.systemManagement.service;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.Department;

import java.math.BigInteger;

public interface DepartmentManageService {

    ResponseData selectAll();

    ResponseData insertDepartment(Department department);

    ResponseData deleteById(BigInteger id);

    ResponseData updateDepartment(Department department);

}
