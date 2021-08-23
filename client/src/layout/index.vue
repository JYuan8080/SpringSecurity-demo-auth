<!--
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/21 17:09
 * @description：index
 * @update: 2021/8/21 17:09
-->
<template>
  <div class="layout">
    <Sidebar />
    <div class="layout-content">
      <div class="layout-header">
        <div>{{ pagePath }}</div>
        <div>用户信息：{{ userInfo }}</div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item v-for="item in breadcrumb" :key="item.path"
            >{{ item }}
          </el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="app-main">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import { useRoute } from 'vue-router'
import { ref, watch } from 'vue'
import { useStore } from 'vuex'
import Sidebar from './Sidebar/index.vue'

/**
 * 将当前页面的path根据/拆分
 * @param path
 * @returns {[]}
 */
function splitRoutePath(path) {
  const substringPath = path.substring(1, path.length)
  const pathArray = substringPath.split('/')
  const concatArray = []
  for (let i = 0; i < pathArray.length; i++) {
    let itemPath = `/${pathArray[0]}`
    for (let j = 1; j < i + 1; j++) {
      itemPath = `${itemPath}/${pathArray[j]}`
    }
    concatArray.push(itemPath)
  }
  return concatArray
}

/**
 * 遍历路由，将路由名字存入给定数组中
 * @param path
 * @param routes
 * @param array
 * @returns {boolean|*}
 */
function getRoute(path, routes, array) {
  if (routes && routes.length) {
    return routes.some((item) => {
      if (path === item.path) {
        array.push(item.name)
        return true
      }
      return getRoute(path, item.children, array)
    })
  }
  return false
}

/**
 * 遍历路由，将路由名字存入给定数组中
 * @param pathArray
 * @param routes
 * @param array
 */
function getRouteNameByPath(pathArray, routes, array) {
  if (pathArray && pathArray.length) {
    pathArray.forEach((path) => {
      getRoute(path, routes, array)
    })
  }
}

/**
 * 根据当前路径取寻找它涉及到的route，并获取名字，这边path是经过特殊涉及的，一级只会有一个/，二级会有两个，以此类推
 * @param path
 * @param routes
 */
function getRouteAndFather(path, routes) {
  const temp = []
  const pathArray = splitRoutePath(path)
  getRouteNameByPath(pathArray, routes, temp)
  return temp
}

export default {
  name: 'index',
  components: {
    Sidebar
  },
  setup() {
    const route = useRoute()
    const store = useStore()
    const pagePath = ref(route.path)
    const breadcrumb = ref(getRouteAndFather(route.path, store.state.user.pages))
    watch(
      () => route.path,
      () => {
        breadcrumb.value = getRouteAndFather(route.path, store.state.user.pages)
        pagePath.value = route.path
      }
    )

    return {
      pagePath,
      breadcrumb,
      userInfo: store.state.user.userInfo
    }
  }
}
</script>

<style lang="scss" scoped>
.layout {
  display: flex;
  min-height: 100vh;
  .layout-content {
    flex: 1;
  }
  .layout-header {
    background-color: #fff;
  }
  .app-main {
    background-color: pink;
    flex: 1;
  }
}
</style>
