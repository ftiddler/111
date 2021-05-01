package com.example.demo.systemManagement.service.impl;

import com.example.demo.common.ResponseData;
import com.example.demo.entity.MaterialInfo;
import com.example.demo.systemManagement.mapper.CodeManageMapper;
import com.example.demo.systemManagement.service.CodeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.List;

@Service
public class CodeManageServiceImpl implements CodeManageService {

    @Autowired
    private CodeManageMapper.Sel codeManageSelMapper;

    @Autowired
    private CodeManageMapper.Del codeManageDelMapper;

    @Autowired
    private CodeManageMapper.Ins codeManageInsMapper;

    @Autowired
    private CodeManageMapper.Upd codeManageUpdMapper;

    @Override
    public ResponseData selectAll() {
        List<MaterialInfo> materialInfos = codeManageSelMapper.selectAll();
        return ResponseData.buildOk(materialInfos);
    }

    @Override
    public ResponseData insertMaterial(MaterialInfo materialInfo) {
        int i = codeManageInsMapper.insertMaterial(materialInfo.getMaterialName(), materialInfo.getModelSpec(),
                materialInfo.getMeasureUnit(), materialInfo.getMaterialType());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData deleteById(Integer id) {
        int i = codeManageDelMapper.deleteById(id);
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }

    @Override
    public ResponseData updateMaterial(MaterialInfo materialInfo) {
        int i = codeManageUpdMapper.updateMaterial(materialInfo.getMaterialName(), materialInfo.getModelSpec(),
                materialInfo.getMeasureUnit(), materialInfo.getMaterialType(), materialInfo.getId());
        if (i > 0) return ResponseData.buildOk();
        return ResponseData.buildError();
    }
}
