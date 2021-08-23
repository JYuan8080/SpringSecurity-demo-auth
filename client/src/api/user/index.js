/**
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/20 21:47
 * @description：index
 * @update: 2021/8/20 21:47
 */
import { requestParams } from '../index'

export function login(username, password) {
  return requestParams('/login', {
    username,
    password
  })
}

export function logout() {
  return requestParams('/login')
}

export function findPagesAndButton() {
  return requestParams('/user/menus')
}

export function findUserInfo() {
  return requestParams('/user/info')
}
