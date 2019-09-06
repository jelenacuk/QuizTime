<template>
    <div>
          <!-- TO DO  -->
          <!-- 
              1. Zastiti da nijedno polje ne moze biti prazno (question, answer)
              2. Pitanje mora imati minimum 2 odgovora
              3. Bar jedan odgovor treba da bude tacan
              4. Pronaci neki lep alert 
          -->
        <navbar></navbar>
        <v-container >
           <!-- FORMA ZA OSNOVNE INFO O KVIZU  -->
           <v-container>
               <form align= "center">
                    <h1 align = "center" style=" width: 200px; display: inline-block;">
                        Quiz Info 
                    </h1>
                    <v-btn  @click="createQuiz()" large color="primary"  >CREATE</v-btn>
                    <v-text-field
                        v-model="title"
                        label="Title"
                        required>
                    </v-text-field>
                    <v-text-field
                        v-model="description"
                        label="Description"
                        required>
                    </v-text-field>
                    <v-text-field
                        v-model="time"
                        label="Time"
                        type="number"
                        required>
                    </v-text-field>
                    <v-combobox
                        v-model="category"
                        :items="categories"
                        label="Select a category">
                    </v-combobox>
                </form>
               <h1>Questions ({{questions.length}})</h1>

                <!-- DIALOG ZA DODAVANJE PITANJA  -->
               <v-dialog v-model="dialog" persistent max-width="600px">
                    <template v-slot:activator="{ on }">
                        <v-btn color="primary" dark v-on="on">Add Question</v-btn>
                    </template>
                    <v-card>
                        <v-card-title>
                            <span class="headline">New Question</span>
                        </v-card-title>
                        <v-card-text>
                            <v-container>
                                <v-row>
                                    <v-col cols="12" style="height: 130px;">
                                        <v-textarea v-model="question_text" shaped outlined label="Question*" required></v-textarea>
                                    </v-col>
                                    <v-col cols="12" sm="7">
                                        <v-text-field
                                            v-model="answer_text"
                                            label="Answer*"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="12" sm="3">
                                        <v-radio-group v-model="answer_correct"  row>
                                            
                                                <v-radio label="True" value="radio-1"></v-radio>
                                                <v-radio label="False" value="radio-2"></v-radio>
                                            </v-radio-group>
                                    </v-col>
                                    <v-col cols="12" sm="2">
                                        <v-btn class="mt-6" @click="createAnswer()"> Add</v-btn>
                                    </v-col>
                                    <v-col>
                                        <!-- LISTA UNUTAR DIALOGA SA DODATIM ODGOVORIMA  -->
                                        <v-list class="border" >
                                            <v-list-item-group >
                                                
                                                <v-list-item 
                                                    v-for="(answer, i) in answers"
                                                    :key="i"
                                                    :value="answer.text"
                                                    link
                                                >
                                                    <v-list-item-content>
                                                        <v-col cols="12" sm="8">
                                                            <v-list-item-title>{{i+1}}. {{ answer.text }}</v-list-item-title>
                                                        </v-col>
                                                        <v-col cols="12" sm="2">
                                                             <v-icon color="green" v-if="answer.correct == true">mdi-check-bold</v-icon>
                                                             <v-icon color="red" v-if="answer.correct == false">mdi-close</v-icon>
                                                        </v-col>
                                                        <v-col cols="12" sm="2">
                                                            <v-btn @click="deleteAnswer(i)" >Delete</v-btn>
                                                        </v-col>
                                                    </v-list-item-content>

                                                </v-list-item>
                                            </v-list-item-group>
                                        </v-list>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <div class="flex-grow-1"></div>
                            <v-btn @click="closeQuestionDialog()" color="blue darken-1" text >Close</v-btn>
                            <v-btn @click="createQuestion()" color="blue darken-1" text>Save</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-container>

            <!-- LISTA PITANJA -->
            <v-card max-width="544" class="ma-4" style="display: inline-block;" v-for="(question, i) in questions" :key="i" >
                <v-card-title>{{i +1}}) {{question.text}} </v-card-title>
                <v-card-text >
                    <span v-for="(answer, j) in question.answers" :key="j">
                        {{j+1}}.{{answer.text}}
                        <v-icon color="green" v-if="answer.correct == true">mdi-check-bold</v-icon>
                        <br>
                    </span>
                </v-card-text>
                <v-card-actions>
                <v-btn text color="red" @click="deleteQuestion(i)">Delete</v-btn>
                </v-card-actions>
            </v-card>
        
       </v-container>

    </div>
</template>

<script>
import navBar from "./navBar.vue"
import axios from 'axios'
    export default {
        name: "createQuiz",
         components: {
            navbar: navBar,
         },
        data() {
            return {
                title: "",
                description: "",
                category: "",
                time: 0,
                categories: ["General Knoledge", "Science", "Geography", "History", "Music", "Film", "Sport", "Religion", "Books"],

                questions: [],
                correct_cnt: 0,
                question_text: "",

                answers: [],
                answer_text: "",
                answer_correct: NaN,

                dialog: false
            }
           
        },
        methods: {
            createQuiz: function(){
                
                if (this.title== "" || this.description == "" || this.time == ""){
                    alert("Fields can not be empty!");
                }
                else if (this.category == ""){
                    alert("Existing category must be chosen!");
                }
                else if (this.questions.length == 0){
                    alert("0 qusitions !!!");
                }

                var newQuiz = {title: this.title, description: this.description, time: this.time, category: this.category,
                                questions: this.questions}
                //axios zahtev
                var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
                axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
                this.axios.post("http://localhost:8080/createQuiz",newQuiz)
                        .then((response) =>{
                            console.log(response);
                        } );
            },

            createQuestion: function(){
                var newQuestion = { text: this.question_text, answers: this.answers, correct_cnt: this.correct_cnt};
                this.questions.push(newQuestion);
                this.question_text = "";
                this.answers = [];
                this.dialog = false;
                this.correct_cnt = 0;
            },

            closeQuestionDialog:function(){

                this.question_text = "";
                this.answers = [];
                this.correct_cnt = 0;
                this.dialog = false;
            },

            deleteQuestion: function(idx){
                this.questions.splice(idx, 1);
            },

            createAnswer: function(){
                var correct = false;
                if (this.answer_correct == "radio-1"){
                    correct = true;
                    this.correct_cnt+= 1;
                }
                var newAnswer = {text: this.answer_text, correct: correct};
                this.answers.push(newAnswer);
                this.answer_text = "";
                this.answer_correct = NaN;
            },

            deleteAnswer: function(idx){
                this.answers.splice(idx, 1);
            },

        }
    }
</script>

<style scoped>

.border{
    border: solid 1px gray;
      box-shadow:  0 0 10px  rgba(0,0,0,0.6);
      -moz-box-shadow: 0 0 10px  rgba(0,0,0,0.6);
      -webkit-box-shadow: 0 0 10px  rgba(0,0,0,0.6);
      -o-box-shadow: 0 0 10px  rgba(0,0,0,0.6);
      
}
</style>