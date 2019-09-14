<template>
    <div >

        <v-card height="700" width="256" >

        <v-navigation-drawer permanent>
            <v-list-item>
            <v-list-item-content>
                <v-list-item-title class="title">
                Categories
                </v-list-item-title>
                <v-list-item-subtitle>
                Select a category
                </v-list-item-subtitle>
            </v-list-item-content>
            </v-list-item>
  
            <v-divider></v-divider>
            
            <v-list  nav >
                <v-list-item-group v-model="category">
                    <v-list-item 
                        v-for="(item, i) in items"
                        :key="i"
                        :value="item.title"
                        @click="selectCategory(item.title)"
                        link
                    >
                    
                        <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                        </v-list-item-icon>
            
                        <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                        </v-list-item-content>

                    </v-list-item>
                </v-list-item-group>
            </v-list>

        </v-navigation-drawer>
        </v-card>

    </div>
</template>

<script>
    export default {
        name: "sideMenu",
        data () {
            return {
            items: [
                { title: 'General Knowledge', icon: 'mdi-all-inclusive'},
                { title: 'Science', icon: 'mdi-molecule' },
                { title: 'Geography', icon: 'mdi-earth' },
                { title: 'History', icon: 'mdi-sword-cross' },
                { title: 'Music', icon: 'mdi-music' },
                { title: 'Film', icon: 'mdi-movie-open' },
                { title: 'Sport', icon: 'mdi-basketball' },
                { title: 'Religion', icon: 'mdi-account-group' },
                { title: 'Books', icon: 'mdi-book' },
            ],
            right: null,
            category: 'General Knowledge'
            }
        },
        methods: {
            selectCategory: function(value){
                this.$emit('clicked', value);
            }
        }
    }
</script>

<style scoped>

.active{
    background-color: aqua;
}

</style>