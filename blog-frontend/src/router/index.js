import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
import BlogListView from "@/views/BlogListView.vue";
import BlogDetailView from "@/views/BlogDetailView.vue";
import BlogEdit from "@/views/BlogEdit.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: {name: 'BlogList'}
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/blog-list',
    name: 'BlogList',
    component: BlogListView
  },
  {
    path: '/blog/add',
    name: 'BlogAdd',
    component: BlogEdit,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/blog/:blogId',
    name: 'BlogDetail',
    component: BlogDetailView
  },
  {
    path: '/blog/:blogId/edit',
    name: 'BlogEdit',
    component: BlogEdit,
    meta: {
      requireAuth: true
    }
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
