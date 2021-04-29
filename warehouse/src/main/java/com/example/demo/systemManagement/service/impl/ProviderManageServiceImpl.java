package com.example.demo.systemManagement.service.impl;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.Supplier;
import com.example.demo.systemManagement.mapper.ProviderManageMapper;
import com.example.demo.systemManagement.service.ProviderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Minli Hong
 * @date 2021-04-26
 */
@Service
public class ProviderManageServiceImpl implements ProviderManageService {

    @Autowired
    private ProviderManageMapper providerManageMapper;

    @Override
    public ResponseData selectAll() {
        List<Supplier> suppliers = providerManageMapper.selectAll();
        return ResponseData.buildOk(suppliers);
    }

    @Override
    public ResponseData insertSupplier(Supplier supplier) {
        int i = providerManageMapper.insertSupplier(supplier.getRegisNumber(), supplier.getSupName(), supplier.getSupType(),
                supplier.getAddressPostcode(), supplier.getContacts(), supplier.getPhone(), supplier.getRemark());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData deleteById(Integer id) {
        int i = providerManageMapper.deleteById(id);
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData updateSupplier(Supplier supplier) {
        int i = providerManageMapper.updateSupplier(supplier.getRegisNumber(), supplier.getSupName(), supplier.getSupType(),
                supplier.getAddressPostcode(), supplier.getContacts(), supplier.getPhone(), supplier.getRemark(), supplier.getId());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }
}
