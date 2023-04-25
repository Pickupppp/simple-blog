import axios from 'axios'
import ElementUI from "element-ui";
import router from "@/router";
import store from "@/store";

axios.defaults.baseURL = 'http://localhost:8081'

axios.interceptors.response.use(response => {
    let res = response.data

    if (res.code === 200) {
      return response
    } else {
      ElementUI.Message.error('出现了错误T_T')
      return Promise.reject(response.data.msg);
    }
  },
  error => {
    if (error.response.data) {
      error.message = error.response.data.msg;
    }
    if (error.response.status === 401) {
      store.commit('REMOVE_INFO')
      router.push('/login')
    }

    ElementUI.Message.error(error.message, {duration: 3 * 1000})
    return Promise.reject(error)
  }
)