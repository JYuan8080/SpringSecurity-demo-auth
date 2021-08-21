<!--
 * @name: SidebarItem
 * @author:  JYuan
 * @date: 2021/8/21 18:24
 * @description：SidebarItem
 * @update: 2021/8/21 18:24
-->
<template>
  <el-submenu :index="item.path">
    <template #title>
      <i class="el-icon-location" />
      <span>导航一</span>
      <span>{{ pages }}</span>
    </template>
    <el-menu-item-group>
      <template #title>分组一</template>
      <el-menu-item index="1-1">选项1</el-menu-item>
      <el-menu-item index="1-2">选项2</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group title="分组2">
      <el-menu-item index="1-3">选项3</el-menu-item>
    </el-menu-item-group>
    <el-submenu index="1-4">
      <template #title>选项4</template>
      <el-menu-item index="1-4-1">选项1</el-menu-item>
    </el-submenu>
  </el-submenu>
  <el-menu-item index="2">
    <i class="el-icon-menu" />
    <template #title>导航二</template>
  </el-menu-item>
  <el-menu-item index="4">
    <i class="el-icon-setting" />
    <template #title>导航四</template>
  </el-menu-item>
  <el-submenu index="5">
    <template #title>
      <i class="el-icon-location" />
      <span>导航一</span>
    </template>
    <el-menu-item-group>
      <template #title>分组一</template>
      <el-menu-item index="5-1">选项1</el-menu-item>
      <el-menu-item index="5-2">选项2</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group title="分组2">
      <el-menu-item index="5-3">选项3</el-menu-item>
    </el-menu-item-group>
  </el-submenu>
</template>

<script>
export default {
  name: 'SidebarItem',
  props: ['item']
}
</script>

<style scoped></style>
