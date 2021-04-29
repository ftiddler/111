import request from '@/utils/request'

export function selectAll() {
  return request({
    url: 'purchaseInfoManage/selectAll',
    method: 'get'
  })
}

export function insertPurchaseInfo(approver, closing_date, auditor, prepare) {
  return request({
    url: 'purchaseInfoManage/insertPurchaseInfo',
    method: 'post',
    data: {
      approver: approver,
      closing_date: closing_date,
      auditor: auditor,
      prepare: prepare
    }
  })
}

export function deleteById(id) {
  return request({
    url: 'purchaseInfoManage/deleteById',
    method: 'post',
    params: {
      id: id
    }
  })
}

export function updatePurchaseInfo(approver, closing_date, auditor, prepare, id) {
  return request({
    url: 'purchaseInfoManage/updatePurchaseInfo',
    method: 'post',
    data: {
      approver: approver,
      closing_date: closing_date,
      auditor: auditor,
      prepare: prepare,
      id: id
    }
  })
}
