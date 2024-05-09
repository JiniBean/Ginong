const { createApp } = Vue;
import Repository from "/js/module/StockRepository.js";
let baseUrl = window.location.origin;
createApp({
    data(){
        return{
            current:false,
            amount:false,
            list:[]
        }
    },
    methods:{
        currentHandler(){
            this.current = this.current ? false:true;
            this.amount = null;
            this.getList();

        },
        amountHandler(){
            this.amount = this.amount ? false:true;
            this.current = false;
            this.getList();
        },
        async getList(){
            // let repository = new Repository;
            // let list = await repository.findAll(this.amount, this.current);

            let url = `${baseUrl}/api/stock?`;
            if(this.amount)
                url += `a=${this.amount}`
            else
                url += `c=${this.current}`

            console.log(url);
            let response = await fetch(url, null);
            let list =  await response.json();
            this.list = list;
        }
    },
    mounted(){
        this.getList();
    }
}).mount('main');