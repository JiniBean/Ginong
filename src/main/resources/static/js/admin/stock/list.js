const { createApp } = Vue;
import Repository from "/js/module/StockRepository.js";
let baseUrl = window.location.origin;
createApp({
    data(){
        return{
            current:false,
            amount:false,
            list:[],
            query: ""
        }
    },
    methods:{
        currentHandler(){
            this.current = !this.current;
            this.amount = null;
            this.getList();

        },
        amountHandler(){
            this.amount = !this.amount;
            this.current = false;
            this.getList();
        },
        move(id){
          console.log(id);
        },
        async getList(){
            let repository = new Repository;
            this.list = await repository.findAll(this.query, this.amount, this.current);
        }
    },
    mounted(){
        this.getList();
    }
}).mount('main');