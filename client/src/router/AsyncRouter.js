/**
 * @name: AsyncRouter
 * @author:  JYuan
 * @date: 2021/8/21 14:50
 * @description：需要根据权限动态判断的路由
 * @update: 2021/8/21 14:50
 */
import Layout from '@/layout/index.vue'
// 如果必须显示顶层目录，则指定一个alwaysShow属性，如果不指定，当子菜单只有一个时，将会忽略上层菜单
export default [
  {
    path: '/customer',
    name: '客户',
    alwaysShow: true, // 是否总是显示
    component: Layout,
    meta: {
      icon: ''
    },
    children: [
      {
        path: '/customer/list',
        name: '客户列表',
        component: () => import('@/views/customer/list/index.vue'),
        meta: {
          icon: ''
        }
      },
      {
        path: '/customer/detail',
        name: '客户详情',
        component: () => import('@/views/customer/detail/index.vue'),
        meta: {
          icon: ''
        }
      }
    ]
  },
  {
    path: '/order',
    name: '订单',
    alwaysShow: true,
    component: Layout,
    meta: {
      icon: ''
    },
    children: [
      {
        path: '/order/list',
        name: '订单列表',
        component: () => import('@/views/order/list/index.vue'),
        meta: {
          icon: ''
        }
      },
      {
        path: '/order/detail',
        name: '订单详情',
        component: () => import('@/views/order/detail/index.vue'),
        meta: {
          icon: ''
        }
      }
    ]
  },
  {
    path: '/setting',
    name: '设置',
    alwaysShow: true,
    component: Layout,
    meta: {
      icon: ''
    },
    children: [
      {
        path: '/setting/user',
        name: '用户设置',
        meta: {
          icon: ''
        },
        children: [
          {
            path: '/setting/user/account',
            name: '账号设置',
            component: () => import('@/views/setting/user/account/index.vue'),
            meta: {
              icon: ''
            }
          },
          {
            path: '/setting/user/password',
            name: '密码设置',
            component: () => import('@/views/setting/user/password/index.vue'),
            meta: {
              icon: ''
            }
          }
        ]
      },
      {
        path: '/setting/role',
        name: '角色设置',
        component: () => import('@/views/setting/role/index.vue'),
        meta: {
          icon: ''
        }
      },
      {
        path: '/setting/menu',
        name: '菜单设置',
        component: () => import('@/views/setting/menu/index.vue'),
        meta: {
          icon: ''
        }
      }
    ]
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404'
  }
]
