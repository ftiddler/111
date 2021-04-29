package com.example.demo.systemManagement.controller;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.PurchaseInfo;
import com.example.demo.systemManagement.service.PurchaseInfoManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author Minli Hong
 * @date 2021-04-27
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/purchaseInfoManage")
public class PurchaseInfoManageController {

    @Autowired
    private PurchaseInfoManageService purchaseInfoManageService;

    @GetMapping("/selectAll")
    public ResponseData selectAll() {
        return purchaseInfoManageService.selectAll();
    }

    @PostMapping("/insertPurchaseInfo")
    public ResponseData insertPurchaseInfo(@RequestBody PurchaseInfo purchaseInfo) {
        return purchaseInfoManageService.insertPurchaseInfo(purchaseInfo);
    }

    @PostMapping("/deleteById")
    public ResponseData deleteById(@RequestParam("id") Integer id) {
        return purchaseInfoManageService.deleteById(id);
    }

    @PostMapping("updatePurchaseInfo")
    public ResponseData updatePurchaseInfo(@RequestBody PurchaseInfo purchaseInfo) {
        return purchaseInfoManageService.updatePurchaseInfo(purchaseInfo);
    }
}
