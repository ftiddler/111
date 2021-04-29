import request from '@/utils/request'

// 获取全部用户
export function selectAll() {
  return request({
    url: 'user/selectAll',
    method: 'get'
  })
}

// 更新用户信息
export function changePasswordByAccount(password, account) {
  return request({
    url: 'user/changePasswordByAccount',
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
    url: 'user/insertUser',
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
    url: 'user/deleteByAccount',
    method: 'post',
    params: {
      account: account
    }
  })
}