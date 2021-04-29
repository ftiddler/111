package com.example.demo.systemManagement.controller;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.Supplier;
import com.example.demo.systemManagement.service.ProviderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/providerManage")
public class ProviderManageController {

    @Autowired
    private ProviderManageService providerManageService;

    @GetMapping("/selectAll")
    public ResponseData selectAll() { return providerManageService.selectAll(); }

    @PostMapping("/insertSupplier")
    public ResponseData insertSupplier(@RequestBody Supplier supplier) {
        return providerManageService.insertSupplier(supplier);
    }

    @PostMapping("/deleteById")
    public ResponseData deleteById(@RequestParam("id") Integer id) { return providerManageService.deleteById(id); }

    @PostMapping("/updateSupplier")
    public ResponseData updateSupplier(@RequestBody Supplier supplier){
        return providerManageService.updateSupplier(supplier);
    }
}
