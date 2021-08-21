<!--
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/21 18:11
 * @description：index
 * @update: 2021/8/21 18:11
-->
<template>
  <el-menu
    :uniqueOpened="true"
    default-active="1-1"
    class="sidebar"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    router
  >
    <SidebarItem v-for="item in pages" :key="item.path" :item="item" />
  </el-menu>
</template>

<script>
import { useStore } from 'vuex'
import SidebarItem from './SidebarItem.vue'

export default {
  name: 'index',
  components: {
    SidebarItem
  },
  setup() {
    const store = useStore()
    const { pages } = store.state.user
    return {
      pages
    }
  }
}
</script>

<style scoped></style>
