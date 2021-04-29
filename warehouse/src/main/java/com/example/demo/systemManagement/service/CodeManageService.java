package com.example.demo.systemManagement.service;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.MaterialInfo;

public interface CodeManageService {

    ResponseData selectAll();

    ResponseData insertMaterial(MaterialInfo materialInfo);

    ResponseData deleteById(Integer id);

    ResponseData updateMaterial(MaterialInfo materialInfo);
}
