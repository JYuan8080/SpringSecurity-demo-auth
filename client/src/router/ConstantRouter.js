/**
 * @name: constantRouter
 * @author:  JYuan
 * @date: 2021/8/20 19:46
 * @description：constantRouter
 * @update: 2021/8/20 19:46
 */
import Layout from '@/layout/index.vue'

export default [
  {
    path: '/login',
    name: '登录页',
    component: () => import('@/views/login/index.vue')
  },
  {
    path: '/404',
    name: '错误页面',
    component: () => import('@/views/404/index.vue')
  }
]
