import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Tarot from '@/components/Tarot'
import ResetPassword from '@/components/ResetPassword'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path:'/resetpassword',
      name:'ResetPassword',
      component:ResetPassword
    },
    {
      path: '/tarot',
      name: 'Tarot',
      component: Tarot
    }
  ]
})
