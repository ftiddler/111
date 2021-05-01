import request from '@/utils/request'

export function selectAll() {
  return request({
    url: '/departmentManage/selectAll',
    method: 'get'
  })
}

export function insertDepartment(departmentName, principal) {
  return request({
    url: '/departmentManage/insertDepartment',
    method: 'post',
    data: {
      departmentName: departmentName,
      principal: principal
    }
  })
}

export function deleteById(id) {
  return request({
    url: '/departmentManage/deleteById',
    method: 'post',
    params: {
      id: id
    }
  })
}

export function updateDepartment(departmentName, principal, id) {
  return request({
    url: '/departmentManage/updateDepartment',
    method: 'post',
    data: {
      departmentName: departmentName,
      principal: principal,
      id: id
    }
  })
}

