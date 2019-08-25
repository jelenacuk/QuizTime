<template>
    <div>
        <table>
            <tr>
                <td>Username</td>
                <td><input type="text" v-model="username"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" v-model="email"/></td>
            </tr>
            <tr>
                <td>FirstName</td>
                <td><input type="text" v-model="firstName"/></td>
            </tr>
            <tr>
                <td>Last name</td>
                <td><input type="text" v-model="lastName"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" v-model="password"/></td>
            </tr>
            <tr>
                <td>Confirm password</td>
                <td><input type="password" v-model="confirmPassword"/></td>
            </tr>
            <tr>
                <td><button @click="register">Register</button></td>
                <td><button @click="back">Back</button></td>
            </tr>
        </table>

    </div>
</template>

<script>
    export default {
        name: "register",
        data(){
            return{
                username:"",
                password:"",
                email:"",
                firstName:"",
                lastName:"",
                confirmPassword:""
            }
        },
        methods:{
            register(){
                this.axios.post("http://localhost:8080/register",{"username":this.username,"password":this.password,"firstName":this.firstName,"lastName":this.lastName,"email":this.email})
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