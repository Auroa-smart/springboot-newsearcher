import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'
import store from '@/store'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component:()=>import('../views/Manage'),
    redirect:"/home", //重定向到home
    children:[
      {
        path: 'home',
        name: '主页',
        component:()=>import('../views/Home'),
      },
      {path: 'user', name: '用户管理', component:()=>import('../views/User')},
      {
        path: 'detail',
        name: '详情',
        component: () => import('../views/NewsDetail'),
      },

    ]
  },
  {
    path: '/tool',
    name: 'Tool',
    component:()=>import('../views/Manage'),
    redirect:"/sort", //重定向到home
    children:[
      {
        path: '/sort',
        name: '分类搜索',
        component:()=>import('../views/NewsSort'),
      },
    ]
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
        name: 'Register',
      component: () => import('../views/Register.vue')
  },
]
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫
router.beforeEach((to,from,next)=>{
  localStorage.setItem("currentPathName",to.name)  //设置当前的路由名称
  store.commit("setPath") //触发store的数据更新
  next()

})

export default router
