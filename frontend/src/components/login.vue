<template>
    <div>
        <table>
            <tr>
                <td>Username</td>
                <td><input type="text" v-model="username"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" v-model="password"/></td>
            </tr>
            <tr>
                <td><button @click="login">Register</button></td>
                <td><button @click="back">Back</button></td>
            </tr>
        </table>
    </div>
</template>

<script>
    export default {
        name: "login",
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
                    } );
            },
            back(){
                this.$router.push("/")
            }

        }
    }
</script>

<style scoped>

</style>