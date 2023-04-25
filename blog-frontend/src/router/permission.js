import router from "@/router/index";

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requireAuth)) {
    let token = localStorage.getItem('token')
    console.log('token is', token)
    if (token === 'undefined') {
      console.log('没有 token')
      // 不存在跳转到登录
      next({
        path: '/login'
      })

    } else {
      console.log('token 存在')
      next()
    }
  } else {
    console.log('不需要token')
    next()
  }
})