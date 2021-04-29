import Layout from '@/layout'

const systemManageRouter = {
  path: '/system-management',
  component: Layout,
  redirct: 'system-management/coding-management',
  name: '系统管理',
  meta: { title: '系统管理', icon: 'el-icon-s-management', permissions: ['6'] },
  children: [
    {
      path: 'coding-management',
      name: '物料编码管理',
      component: () => import('@/views/system-management/coding-management'),
      meta: { title: '物料编码管理', icon: 'el-icon-s-order' }
    },
    {
      path: 'supplier-management',
      name: '供应商管理',
      component: () => import('@/views/system-management/supplier-management'),
      meta: { title: '供应商管理', icon: 'el-icon-user-solid' }
    },
    {
      path: 'repository-initialzation',
      name: '仓库初始化',
      component: () => import('@/views/system-management/repository-initialization'),
      meta: { title: '仓库初始化', icon: 'el-icon-s-home' }
    },
    {
      path: 'deadline-management',
      name: '采购计划截止时间管理',
      component: () => import('@/views/system-management/deadline-management'),
      meta: { title: '采购计划截止时间管理', icon: 'el-icon-warning' }
    },
    {
      path: 'department-management',
      name: '部门管理',
      component: () => import('@/views/system-management/department-management'),
      meta: { title: '部门管理', icon: 'el-icon-menu' }
    },
    {
      path: 'user-management',
      name: '用户管理',
      component: () => import('@/views/system-management/user-management'),
      meta: { title: '用户管理', icon: 'el-icon-user-solid' }
    },
    {
      path: 'authority-management',
      name: '权限管理',
      component: () => import('@/views/system-management/authority-management'),
      meta: { title: '权限管理', icon: 'el-icon-success' }
    }
  ]
}

export default systemManageRouter

