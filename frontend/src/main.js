import Vue from 'vue'
import App from './App.vue'
import router from './router.js';
import 'vuetify/dist/vuetify.min.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import vuetify from './plugins/vuetify';


Vue.config.productionTip = false

//export const dataBus = new Vue();

new Vue({
    router,
    vuetify,
    render: h => h(App)
}).$mount('#app');
Vue.use(VueAxios, axios);
