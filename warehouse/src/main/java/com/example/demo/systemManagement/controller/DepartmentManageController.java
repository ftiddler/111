package com.example.demo.systemManagement.controller;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.Department;
import com.example.demo.systemManagement.service.DepartmentManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/departmentManage")
@PreAuthorize("hasAuthority('6')")
public class DepartmentManageController {

    @Autowired
    DepartmentManageService departmentManageService;

    @GetMapping("/selectAll")
    public ResponseData selectAll() {
        return departmentManageService.selectAll();
    }

    @PostMapping("/insertDepartment")
    public ResponseData insertDepartment(@RequestBody Department department) {
        return departmentManageService.insertDepartment(department);
    }

    @PostMapping("/deleteById")
    public ResponseData deleteById(@RequestParam("id") BigInteger id) {
        return departmentManageService.deleteById(id);
    }

    @PostMapping("/updateDepartment")
    public ResponseData updateDepartment(@RequestBody Department department) {
        return departmentManageService.updateDepartment(department);
    }
}
