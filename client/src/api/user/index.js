/**
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/20 21:47
 * @description：index
 * @update: 2021/8/20 21:47
 */
import { requestParams } from '../index'

export function login(username, password) {
  requestParams('/login', {
    username,
    password
  })
}

export function logout() {
  requestParams('/login')
}
