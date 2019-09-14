<template>
    <div >
         <!-- TO DO  -->
          <!-- 
              1. Paging
          -->
        <navbar></navbar>
        <v-container>
            <v-row justify="start" align="start">

                <v-col cols="3">
                     <sidemenu   @clicked="onCategorySelection"></sidemenu>
                </v-col>

                <v-col >
                    <v-container>
                        <v-card width="500px" class="mx-auto" outlined>
                        <v-card-text>
                                <table  class="filterForm" >
                                    <tr>
                                        <td>
                                            <v-label>
                                               Questions:
                                            </v-label>
                                        </td>
                                        
                                        <td>
                                            <v-text-field v-model="minQuestions"
                                                type="number"
                                                label="Min number">
                                            </v-text-field>
                                        </td>
                                       
                                        <td>
                                            <v-text-field v-model="maxQuestions"
                                                type="number"
                                                label="Max number">
                                            </v-text-field>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <v-label>
                                               Time Limited:
                                            </v-label>
                                        </td>
                                        <td colspan="2">
                                             <v-radio-group v-model="time"  row>
                                                <v-radio label="Yes"  value="radio-1" style="margin-right: 50px;"></v-radio>
                                                <v-radio label="NO" value="radio-2"></v-radio>
                                            </v-radio-group>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" @click="applyFilters" style="text-align: center"><v-btn  small color="success" >Apply Filters</v-btn></td>
                                    </tr>        
                                </table>
                        </v-card-text>
         
                    </v-card>
                    </v-container>
                    <br>
                    <v-card v-for="(quiz, i) in this.quizes" :key="i"   class="mx-auto quizInfo" >
                        <v-row class="mx-12 " >
                            <v-col >
                                <h2>{{quiz.title}}</h2>
                                <v-btn large color="success" >Play</v-btn>
                            </v-col>
                            <v-col class="my-auto">
                                Description: {{quiz.description}} <br>
                                Category: {{quiz.category}} <br>
                                Creator: {{quiz.creator}}
                            </v-col>
                        </v-row>
                    </v-card>
                </v-col>
            </v-row>
           
        </v-container>

    </div>
</template>

<script>
    import navBar from "./navBar.vue"
    import sideMenu from "./sideMenu.vue"
    import axios from 'axios'
    export default {
        name: "frontPage",
        components: {
            navbar: navBar,
            sidemenu: sideMenu
        },
        data() {
            return {
                quizes: [],
                category: "General Knowledge",
                minQuestions: "",
                maxQuestions: "",
                time: "radio-1",
                paging: 1
            }
        },

        created: function(){
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/getQuizes/1")
            .then(response => {
                this.quizes = response.data
            });
        },

        methods: {

            applyFilters: function(){
                var limited = true;
                if (this.time == "radio-2"){
                    limited = false;
                }
                var filter = {minQuestions: this.minQuestions, maxQuestions: this.maxQuestions, category: this.category, time: limited}
                var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
                axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
                this.axios.post("http://localhost:8080/searchQuizes", filter)
                .then(response => {
                    this.quizes = response.data
                });
            },

            onCategorySelection: function(value) {
                this.category = value;
                this.applyFilters();
            }
        }
    }
</script>

<style scoped>

.quizInfo{
    border: solid 1px lime;
      box-shadow:  0 0 10px  rgba(0,0,0,0.6);
      -moz-box-shadow: 0 0 10px  rgba(0,0,0,0.6);
      -webkit-box-shadow: 0 0 10px  rgba(0,0,0,0.6);
      -o-box-shadow: 0 0 10px  rgba(0,0,0,0.6);
      width: 700px; margin-bottom: 10px;
}

.filterForm{
    text-align: left
}



</style>