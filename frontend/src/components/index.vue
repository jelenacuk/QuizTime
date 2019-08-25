<template>
    <div>
        <navBar></navBar>
            <v-container >
                <v-row align="center" class="largeHeight">
                    <v-col  md="6" sm="12" xs="10"  align="center">
                        <v-btn @click="login" class="firstButton">Login</v-btn>
                    </v-col>
                    <v-col   md="6" sm="12" xs="10" align="center">
                        <v-btn @click="register">Register</v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </div>
</template>

<script>
    import navBar from "./navBar"

    export default {
        name: "index",
        components: {navBar},
        methods:{
            login(){
                this.$router.push("/login");
            },
            register(){
                this.$router.push("/register")
            }
        }
    }
</script>

<style scoped>
    .largeHeight {
        height: 60vh;
        margin-top: 15vh;
    }
</style>