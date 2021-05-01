package com.example.demo.systemManagement.service.impl;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.Department;
import com.example.demo.systemManagement.mapper.DepartmentManageMapper;
import com.example.demo.systemManagement.service.DepartmentManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class DepartmentManageServiceImpl implements DepartmentManageService {

    @Autowired
    private DepartmentManageMapper departmentManageMapper;

    @Override
    public ResponseData selectAll() {
        List<Department> departments = departmentManageMapper.selectAll();
        return ResponseData.buildOk(departments);
    }

    @Override
    public ResponseData insertDepartment(Department department) {
        int i = departmentManageMapper.insertDepartment(department.getDepartmentName(), department.getPrincipal());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData deleteById(BigInteger id) {
        int i = departmentManageMapper.deleteById(id);
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData updateDepartment(Department department) {
        int i = departmentManageMapper.updateDepartment(department.getDepartmentName(), department.getPrincipal(), department.getId());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }
}
