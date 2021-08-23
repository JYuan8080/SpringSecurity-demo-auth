/**
 * @name: user
 * @author:  JYuan
 * @date: 2021/8/21 12:52
 * @description：user
 * @update: 2021/8/21 12:52
 */
import { findPagesAndButton } from '@/api/user'
import AsyncRouter from '@/router/AsyncRouter'
import ResValidator from '../../utils/ResValidator'
import router from '@/router/index'
import { findUserInfo } from '../../api/user'

/**
 * 比对两张路由表
 * @param route
 * @param routes
 * @returns {*}
 */
function findRoute(route, routes) {
  return routes.some((item) => {
    if (item.name === route.name) {
      return true
    }
    if (item.children && item.children.length) {
      return findRoute(route, item.children)
    }
    return false
  })
}

/**
 * 获取当前用户具有的权限
 * @param routes
 * @param menus
 * @returns {[]|null}
 */
function getPages(routes, menus) {
  const temp = []
  routes.forEach((item) => {
    if (findRoute(item, menus)) {
      temp.push(item)
      if (item.children && item.children.length) {
        item.children = getPages(item.children, menus)
      } else {
        item.children = null
      }
    }
  })
  return temp.length ? temp : null
}

/**
 * 将按钮添加到一个空数组中
 * @param menus
 * @param array
 */
function findButtons(menus, array) {
  if (menus && menus.length) {
    menus.forEach((item) => {
      if (item.type === 1) {
        array.push(item.code)
      } else if (item.children && item.children.length) {
        findButtons(item.children, array)
      }
    })
  }
}

/**
 * 获取按钮
 * @param menus
 * @returns {[]}
 */
function getButtons(menus) {
  const list = []
  findButtons(menus, list)
  return list
}

/**
 * 为路由添加redirect属性
 * @param accessRoutes
 */
function addRedirect(accessRoutes) {
  if (accessRoutes && accessRoutes.length) {
    accessRoutes.forEach((item) => {
      if (item.children && item.children.length) {
        item.redirect = item.children[0].path
      }
      addRedirect(item.children)
    })
  }
}

export default {
  state() {
    return {
      userInfo: null,
      pages: null,
      buttons: null
    }
  },
  getters: {},
  mutations: {
    setUserInfo(state, payload) {
      state.userInfo = payload
    },
    setPages(state, payload) {
      state.pages = payload
    },
    setButtons(state, payload) {
      state.buttons = payload
    }
  },
  actions: {
    getUserInfo() {
      return new Promise((resolve, reject) => {
        findUserInfo()
          .then((value) => {
            ResValidator(value, (data) => {
              this.commit('setUserInfo', data)
              resolve(true)
            })
          })
          .catch(() => {
            reject()
          })
      })
    },
    getPagesAndButtons() {
      return new Promise((resolve, reject) => {
        findPagesAndButton()
          .then((value) => {
            if (
              ResValidator(value, (data) => {
                const pages = getPages(AsyncRouter, data)
                addRedirect(pages)
                this.commit('setPages', pages)
                this.commit('setButtons', getButtons(data))
                if (pages && pages.length) {
                  pages.forEach((item) => router.addRoute(item))
                }
                router.addRoute(AsyncRouter[AsyncRouter.length - 1])
              })
            ) {
              resolve(true)
            }
          })
          .catch(() => {
            reject()
          })
      })
    }
  }
}
