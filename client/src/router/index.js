/**
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/20 19:44
 * @description：index
 * @update: 2021/8/20 19:44
 */
import { createRouter, createWebHistory } from 'vue-router'
import constantRouter from './constantRouter'

export default createRouter({
  history: createWebHistory(),
  routes: constantRouter
})
