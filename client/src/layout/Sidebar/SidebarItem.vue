<!--
 * @name: SidebarItem
 * @author:  JYuan
 * @date: 2021/8/21 18:24
 * @description：SidebarItem
 * @update: 2021/8/21 18:24
-->
<template>
  <el-submenu v-if="showSubmenuPage(item)" :index="getIndex(item)">
    <template #title>
      <i :class="getIcon(item)" />
      <span>{{ getTitle(item) }}</span>
    </template>
    <SidebarItem v-for="item in item.children" :key="item.path" :item="item" />
  </el-submenu>
  <el-menu-item v-if="showItemPage(item)" :index="getIndex(item)">
    <i :class="getIcon(item)" />
    <template #title>{{ getTitle(item) }}</template>
  </el-menu-item>
</template>

<script>
export default {
  name: 'SidebarItem',
  props: ['item'],
  setup() {
    return {
      showSubmenuPage(item) {
        if (item.children && item.children.length) {
          if (item.children.length === 1) {
            return !!item.alwaysShow
          }
          return true
        }
        return false
      },
      showItemPage(item) {
        if (!item.children) {
          return true
        }
        return item.children && item.children.length === 1 && !item.alwaysShow
      },
      getIcon(item) {
        if (item.children && item.children.length === 1 && !item.alwaysShow) {
          if (item.children[0].meta && item.children[0].meta.icon) {
            return item.children[0].meta.icon
          }
        }
        if (item.meta && item.meta.icon) {
          return item.meta.icon
        }
        return false
      },
      getTitle(item) {
        if (item.children && item.children.length === 1 && !item.alwaysShow) {
          return item.children[0].name
        }
        return item.name
      },
      getIndex(item) {
        if (item.children && item.children.length === 1 && !item.alwaysShow) {
          return item.children[0].path
        }
        return item.path
      }
    }
  }
}
</script>

<style scoped></style>
