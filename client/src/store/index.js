/**
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/20 19:44
 * @description：index
 * @update: 2021/8/20 19:44
 */
import { createStore } from 'vuex'
import user from './module/user'

const store = createStore({
  modules: {
    user
  }
})

export default store
