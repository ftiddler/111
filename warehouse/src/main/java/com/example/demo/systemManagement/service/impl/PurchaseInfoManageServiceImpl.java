package com.example.demo.systemManagement.service.impl;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.PurchaseInfo;
import com.example.demo.systemManagement.mapper.PurchaseInfoManageMapper;
import com.example.demo.systemManagement.service.PurchaseInfoManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Minli Hong
 * @date  2021-04-27
 */
@Service
public class PurchaseInfoManageServiceImpl implements PurchaseInfoManageService {

    @Autowired
    private PurchaseInfoManageMapper purchaseInfoManageMapper;

    @Override
    public ResponseData selectAll() {
        List<PurchaseInfo> purchaseInfos = purchaseInfoManageMapper.selectAll();
        return ResponseData.buildOk(purchaseInfos);
    }

    @Override
    public ResponseData insertPurchaseInfo(PurchaseInfo purchaseInfo) {
        int i = purchaseInfoManageMapper.insertPurchaseInfo(purchaseInfo.getApprover(), purchaseInfo.getClosingDate(),
                purchaseInfo.getAuditor(), purchaseInfo.getPrepare());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData deleteById(Integer id) {
        int i = purchaseInfoManageMapper.deleteById(id);
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData updatePurchaseInfo(PurchaseInfo purchaseInfo) {
        int i = purchaseInfoManageMapper.updatePurchaseInfo(purchaseInfo.getApprover(), purchaseInfo.getClosingDate(),
                purchaseInfo.getAuditor(), purchaseInfo.getPrepare(), purchaseInfo.getId());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }
}
