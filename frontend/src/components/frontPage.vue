<template>
    <div >
        <navbar></navbar>
        <v-container>

           

            <v-row justify="start" align="start">

                <v-col>
                     <sidemenu></sidemenu>
                </v-col>
                <v-col>
                </v-col>
            </v-row>
           
        </v-container>

    </div>
</template>

<script>
    import navBar from "./navBar.vue"
    import sideMenu from "./sideMenu.vue"
    export default {
        name: "frontPage",
        components: {
            navbar: navBar,
            sidemenu: sideMenu
        }
    }
</script>

<style scoped>

</style>