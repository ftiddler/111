package com.example.demo.systemManagement.service;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.PurchaseInfo;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Minli Hong
 * @date 2021-04-27
 */
public interface PurchaseInfoManageService {

    ResponseData selectAll();

    ResponseData insertPurchaseInfo(PurchaseInfo purchaseInfo);

    ResponseData deleteById(Integer id);

    ResponseData updatePurchaseInfo(PurchaseInfo purchaseInfo);

}
