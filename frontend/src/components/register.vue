<template>
    <div>
        <navBar></navBar>
        <v-container grid-list-md text-xs-center>
            <v-row>
                <v-col align="center">
                    <v-card>
                        <h1>Register</h1>
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
                                            v-model="email"
                                            label="Email"
                                            required
                                    ></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <v-text-field
                                            v-model="firstName"
                                            label="FirstName"
                                            required
                                    ></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <v-text-field
                                            v-model="lastName"
                                            label="Last Name"
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
                                <td  id="confirm">
                                    <v-text-field
                                            v-model="confirmPassword"
                                            label="Confirm Password"
                                            :type="'password'"
                                            required
                                    ></v-text-field>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <v-btn large color="primary" @click="register">Register</v-btn></td>
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
        name: "register",
        components:{
            navBar
        },
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
                if(this.password===this.confirmPassword && this.password!=""){
                    this.axios.post("http://localhost:8080/auth/register",{"username":this.username,"password":this.password,"firstName":this.firstName,"lastName":this.lastName,"email":this.email})
                        .then((response) =>{
                            console.log(response);
                        } );
                }else{
                   var confirmElement = document.getElementById("confirm");
                   confirmElement.classList.add("notEntered");
                }

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
    .notEntered{
        border: 1px solid red;
    }
</style>