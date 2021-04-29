import request from '@/utils/request'

// 获取全部供应商
export function selectAll() {
  return request({
    url: '/providerManage/selectAll',
    method: 'get'
  })
}

// 更新供应商信息
export function updateSupplier(regisNumber, supName, supType, addressPostcode, contacts, phone, remark, id) {
  return request({
    url: '/providerManage/updateSupplier',
    method: 'post',
    data: {
      regisNumber: regisNumber,
      supName: supName,
      supType: supType,
      addressPostcode: addressPostcode,
      contacts: contacts,
      phone: phone,
      remark: remark,
      id: id
    }
  })
}

// 新增供应商
export function insertSupplier(regisNumber, supName, supType, addressPostcode, contacts, phone, remark) {
  return request({
    url: '/providerManage/insertSupplier',
    method: 'post',
    data: {
      regisNumber: regisNumber,
      supName: supName,
      supType: supType,
      addressPostcode: addressPostcode,
      contacts: contacts,
      phone: phone,
      remark: remark
    }
  })
}

// 删除供应商
export function deleteById(id) {
  return request({
    url: '/providerManage/deleteById',
    method: 'post',
    params: {
      id: id
    }
  })
}
