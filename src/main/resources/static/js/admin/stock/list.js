const { createApp } = Vue;
import Repository from "/js//module/StockRepository";

createApp({
    data(){
        return{
            current:false,
            amount:false,
            list
        }
    },
    methods:{
        currentHandler(){
            this.getList();
            this.current = this.current ? false:true;
        },
        amountHandler(){
            this.getList();
            this.amount = this.amount ? false:true;
        },
        async getList(){
            let repository = new Repository;
            this.list = await repository.getList(this.amount, this.current);
        }
    },
    mounted(){
        getList();
        console.log("마운트 됨");
    }
}).mount('main');