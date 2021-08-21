import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from '@/router/index.js'
import store from '@/store/index.js'
import 'element-plus/lib/theme-chalk/index.css'

const app = createApp(App)
app.use(router).use(store).use(ElementPlus)
router.isReady().then(() => {
  app.mount('#app')
})
