import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from '@/router/index.js'
import 'element-plus/lib/theme-chalk/index.css'

const app = createApp(App)
app.use(router).use(ElementPlus)
router.isReady().then(() => {
  app.mount('#app')
})
