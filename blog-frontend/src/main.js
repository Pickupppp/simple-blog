import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import './axios'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import './router/permission'


Vue.config.productionTip = false

Vue.use(ElementUI).use(mavonEditor)

Vue.prototype.$axios = axios

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
