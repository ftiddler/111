package com.example.demo.systemManagement.controller;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.MaterialInfo;
import com.example.demo.systemManagement.service.CodeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/codeManage")
public class CodeManageController {

    @Autowired
    private CodeManageService codeManageService;

    @GetMapping("/selectAll")
    public ResponseData selectAll() { return codeManageService.selectAll(); }

    @PostMapping("/insertMaterial")
    public ResponseData insertMaterial(@RequestBody MaterialInfo materialInfo) {
        return codeManageService.insertMaterial(materialInfo);
    }

    @PostMapping("/deleteById")
    public ResponseData deleteById(@RequestParam("id") Integer id) { return codeManageService.deleteById(id); }

    @PostMapping("/updateMaterial")
    public ResponseData updateMaterial(@RequestBody MaterialInfo materialInfo) {
        return codeManageService.updateMaterial(materialInfo);
    }
}
