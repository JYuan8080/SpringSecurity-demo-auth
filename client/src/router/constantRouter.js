/**
 * @name: constantRouter
 * @author:  JYuan
 * @date: 2021/8/20 19:46
 * @description：constantRouter
 * @update: 2021/8/20 19:46
 */
export default [
  {
    path: '/',
    name: 'dashboard',
    component: () => import('@/views/dashboard/index.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/index.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/404/index.vue')
  }
]
