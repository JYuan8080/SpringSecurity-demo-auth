<!--
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/21 17:09
 * @description：index
 * @update: 2021/8/21 17:09
-->
<template>
  <!--<Sidebar />-->
  <router-view />
</template>

<script>
import Sidebar from './Sidebar/index.vue'

export default {
  name: 'index',
  components: {
    Sidebar
  }
}
</script>

<style scoped></style>
