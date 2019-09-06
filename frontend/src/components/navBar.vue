<template>
    <div  class="mediumHeight">
        <v-container>

            <v-row justify="start" align="start">
                <v-row>
                     <v-col >
                        <img width="280" height="95vh" src="logo.jpg" alt="No photo"/>
                    </v-col>
                </v-row>
            </v-row>

            <v-row >
                <v-toolbar>
                    <div class="flex-grow-1"></div>
                    <v-toolbar-items>
                        <v-btn text> Home</v-btn>
                        <v-btn text>Make Quiz</v-btn>
                        <v-btn text>Profile</v-btn>
                        <v-btn text>Friends</v-btn>
                        <v-btn text>Log Out</v-btn>
                    </v-toolbar-items>
                </v-toolbar>
            </v-row>  
        </v-container>

    </div>
</template>

<script>
    export default {
        name: "navBar"
    }
</script>

<style scoped>
.mediumHeight{
    height: 30vh;
}

</style>