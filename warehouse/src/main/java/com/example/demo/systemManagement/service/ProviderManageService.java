package com.example.demo.systemManagement.service;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.Supplier;
import org.apache.ibatis.annotations.Param;

/**
 * @author Minli Hong
 * @date 2021-04-26
 */
public interface ProviderManageService {

    ResponseData selectAll();

    ResponseData insertSupplier(Supplier supplier);

    ResponseData deleteById(Integer id);

    ResponseData updateSupplier(Supplier supplier);
}
