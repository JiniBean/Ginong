const { createApp } = Vue;
// import Repository from "/js/module/orderRepository.js";
createApp({
    data(){
        return{
            orderType:1,//기본값
            sortType:null,
            list:[],
            query: ""
        }
    },
    methods:{
        async getList(orderType, sortType){
            this.orderType = orderType;
            this.sortType = sortType
            console.log(sortType)
            console.log(this.sortType)
            // let repository = new Repository;
            // this.list = await repository.findAll(this.query, this.orderType);
        }
    },
    mounted(){
        this.getList(1);
    }
}).mount('main');