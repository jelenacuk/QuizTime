import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'Index',
            component: () => import('./components/index.vue')
        },
        {
            path: '/register',
            name: 'Register',
            component: () => import('./components/register.vue')
        },
        {
            path: '/front',
            name: 'frontPage',
            component: () => import('./components/frontPage.vue')
        },
        {
            path: '/login',
            name: 'Login',
            component: () => import('./components/login.vue')
        },
        {
            path: '/createQuiz',
            name: 'createQuiz',
            component: () => import('./components/createQuiz.vue')
        }

    ]
})