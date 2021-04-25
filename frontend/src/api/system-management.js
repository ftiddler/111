import request from '@/utils/request'

// 选择全部物料
export function selAllMaterial(data) {
  return request({
    url: '/selAllMaterial',
    method: 'get'
  })
}

// 更新物料信息
export function updMaterialInfo(id, matCode, matName, modelSpec, measUnit) {
  return request({
    url: '/updMaterialInfo',
    method: 'post',
    params: {
      id: id,
      matCode: matCode,
      matName: matName,
      modelSpec: modelSpec,
      measUnit: measUnit
    }
  })
}

// 删除物料信息
export function deleteById(id) {
  return request({
    url: '/member/deleteById',
    method: 'post',
    params: {
      id: id
    }
  })
}

// 添加物料信息
export function insertMaterial(matCode, matName, modelSpec, measUnit) {
  return request({
    url: '/member/insertMember',
    method: 'post',
    params: {
      matCode: matCode,
      matName: matName,
      modelSpec: modelSpec,
      measUnit: measUnit
    }
  })
}

// 获取最大物料id
export function selMaxId() {
  return request({
    url: 'member/selMaxId',
    method: 'get'
  })
}
