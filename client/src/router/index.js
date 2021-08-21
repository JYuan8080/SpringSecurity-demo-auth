/**
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/20 19:44
 * @description：index
 * @update: 2021/8/20 19:44
 */
import { createRouter, createWebHistory } from 'vue-router'
import ConstantRouter from './ConstantRouter'
import getToken from '@/utils/CookieUtil'
import store from '@/store/index'

const router = createRouter({
  history: createWebHistory(),
  routes: ConstantRouter
})

router.beforeEach(async (to) => {
  if (to.path === '/login') {
    // todo 目前不做任何处理
    document.title = '登录页'
  }
  if (to.path === '/404') {
    // todo 目前不做任何处理
    document.title = '错误页面'
  }
  const token = getToken()
  if (token) {
    // 存在判断当前用户是否具有该页面权限
    if (!store.state.user.pages) {
      // 刷新页面时重新获取页面权限和按钮权限
      await store.dispatch('getPagesAndButtons')
      router.getRoutes().some((item) => {
        if (to.path === item.path) {
          document.title = item.name
          return true
        }
        return false
      })
      await router.replace(to)
    }
  } else {
    return '/login'
  }
  return true
})
export default router
