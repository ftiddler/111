import Layout from '@/layout'

const systemManageRouter = {
  path: '/system-management',
  component: Layout,
  redirct: 'system-management/coding-management',
  name: '系统管理',
  meta: { title: '系统管理', icon: 'el-icon-s-management', permissions: ['superAdmin', 'departAdmin', 'repoKeeper'] },
  children: [
    {
      path: 'coding-management',
      name: '物料编码管理',
      component: () => import('@/views/system-management/coding-management'),
      meta: { title: '物料编码管理', icon: 'el-icon-s-order', permissions: ['superAdmin', 'departAdmin', 'repoKeeper'] }
    },
    {
      path: 'supplier-management',
      name: '供应商管理',
      component: () => import('@/views/system-management/supplier-management'),
      meta: { title: '供应商管理', icon: 'el-icon-user-solid', permissions: ['superAdmin', 'departAdmin'] }
    },
    {
      path: 'repository-initialzation',
      name: '仓库初始化',
      component: () => import('@/views/system-management/repository-initialization'),
      meta: { title: '仓库初始化', icon: 'el-icon-s-home', permissions: ['superAdmin', 'departAdmin', 'repoKeeper'] }
    },
    {
      path: 'deadline-management',
      name: '采购计划截止时间管理',
      component: () => import('@/views/system-management/deadline-management'),
      meta: { title: '采购计划截止时间管理', icon: 'el-icon-warning', permissions: ['superAdmin', 'departAdmin'] }
    },
    {
      path: 'department-management',
      name: '部门管理',
      component: () => import('@/views/system-management/department-management'),
      meta: { title: '部门管理', icon: 'el-icon-menu', permissions: ['superAdmin'] }
    },
    {
      path: 'user-management',
      name: '用户管理',
      component: () => import('@/views/system-management/user-management'),
      meta: { title: '用户管理', icon: 'el-icon-user-solid', permissions: ['superAdmin', 'departAdmin'] }
    },
    {
      path: 'authority-management',
      name: '权限管理',
      component: () => import('@/views/system-management/authority-management'),
      meta: { title: '权限管理', icon: 'el-icon-success', permissions: ['superAdmin', 'departAdmin'] }
    }
  ]
}

export default systemManageRouter

