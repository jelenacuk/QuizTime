<template>
    <div>
        <navBar></navBar>
        <v-btn >Login</v-btn>
        <v-btn >Register</v-btn>
    </div>
</template>

<script>
    import navBar from "./navBar"
    export default {
        name: "index",
        components:{ navBar}
    }
</script>

<style scoped>

</style>