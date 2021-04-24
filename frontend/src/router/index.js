import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/system-management',
    component: Layout,
    redirct: 'systemManagement/coding-management',
    name: '系统管理',
    meta: { title: '系统管理', icon: 'el-icon-s-management' },
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
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
