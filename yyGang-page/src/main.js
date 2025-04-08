import { createApp } from 'vue'
import { createPinia } from 'pinia' // 뷰의 상태 관리 라이브러리
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.mount('#app')