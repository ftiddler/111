import request from '@/utils/request'

// 选择全部物料
export function selAllMaterial() {
  return request({
    url: '/codeManage/selectAll',
    method: 'get'
  })
}

// 更新物料信息
export function updMaterialInfo(materialCode, materialName, modelSpec, measureUnit, id) {
  return request({
    url: '/codeManage/updateMaterial',
    method: 'post',
    data: {
      materialCode: materialCode,
      materialName: materialName,
      modelSpec: modelSpec,
      measureUnit: measureUnit,
      id: id
    }
  })
}

// 删除物料信息
export function deleteById(id) {
  return request({
    url: '/codeManage/deleteById',
    method: 'post',
    params: {
      id: id
    }
  })
}

// 添加物料信息
export function insertMaterial(materialCode, materialName, modelSpec, measureUnit) {
  return request({
    url: '/codeManage/insertMaterial',
    method: 'post',
    data: {
      materialCode: materialCode,
      materialName: materialName,
      modelSpec: modelSpec,
      measureUnit: measureUnit
    }
  })
}

