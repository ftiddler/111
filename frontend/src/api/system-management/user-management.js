import request from '@/utils/request'

// 获取全部用户
export function selectAll() {
  return request({
    url: '/user/selectAll',
    method: 'get'
  })
}

// 更新用户信息
export function changePasswordByAccount(password, account) {
  return request({
    url: '/user/changePasswordByAccount',
    method: 'post',
    data: {
      password: password,
      account: account
    }
  })
}

// 新增用户
export function insertUser(account, password, username) {
  return request({
    url: '/user/insertUser',
    method: 'post',
    data: {
      account: account,
      password: password,
      username: username
    }
  })
}

// 删除用户
export function deleteByAccount(account) {
  return request({
    url: '/user/deleteByAccount',
    method: 'post',
    params: {
      account: account
    }
  })
}

// 添加角色
export function insertUserRole(userId, departmentName, positionName) {
  return request({
    url: '/user/insertUserRole',
    method: 'post',
    params: {
      userId: userId,
      departmentName: departmentName,
      positionName: positionName
    }
  })
}

// 删除角色
export function deleteUserRole(userId, departmentName, positionName) {
  return request({
    url: '/user/deleteUserRole',
    method: 'post',
    params: {
      userId: userId,
      departmentName: departmentName,
      positionName: positionName
    }
  })
}

// 选择全部部门
export function selectAllDepartments() {
  return request({
    url: '/user/selectAllDepartments',
    method: 'get'
  })
}

// 选择全部职位
export function selectAllPositions() {
  return request({
    url: '/user/selectAllPositions',
    method: 'get'
  })
}

// 根据账号选择全部角色
export function selectRoleByAccount(account) {
  return request({
    url: '/user/selectRoleByAccount',
    method: 'post',
    params: {
      account: account
    }
  })
}
