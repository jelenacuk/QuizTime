<template>
    <div>
        <navBar></navBar>
        <v-container grid-list-md text-xs-center>
            <v-row>
                <v-col align="center">
                    <v-card>
                        <h1>Login</h1>
                        <br/>
                        <table class="px-0 centered">
                            <tr>
                                <td>
                                    <v-text-field
                                            v-model="username"
                                            label="Username"
                                            required
                                    ></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <v-text-field
                                            v-model="password"
                                            label="Password"
                                            :type="'password'"
                                            required
                                    ></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <v-btn large color="primary" @click="login">Log In</v-btn></td>
                                <td><v-btn large color="primary" @click="back">Back</v-btn></td>

                            </tr>
                        </table>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import navBar from "./navBar.vue"
    export default {
        name: "login",
        components:{
          navBar
        },
        data(){
            return{
                username:"",
                password:""
            }
        },
        methods:{
            login(){
                this.axios.post("http://localhost:8080/auth/login",{"username":this.username,"password":this.password})
                    .then((response) =>{
                        console.log(response);
                        localStorage.setItem("jwtToken",response.data.accessToken);
                    } );
            },
            back(){
                this.$router.push("/")
            }

        }
    }
</script>

<style scoped>
.centered{
    margin-left: auto;
    margin-right: auto;
}
</style>