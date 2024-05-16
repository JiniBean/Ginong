const { createApp } = Vue;
import Repository from "/js/module/OrderRepository.js";
createApp({
    data(){
        return{
            orderType:1,//기본값
            sortType:null,
            list:[],
            query: "",
            category: {
                1:'주문',
                2:'교환',
                3:'환불',
                4:'취소'
            }
        }
    },
    methods:{
        async getList(orderType, sortType){
            this.orderType = orderType;
            this.sortType = sortType
            let repository = new Repository;

            if(orderType==1){
                this.list = await repository.findAll(this.query,this.sortType);
                return;
            }

            this.list = await repository.findAllByOption(this.orderType,this.query,this.sortType);

        }
    },
    mounted(){
        this.getList(1);
    }
}).mount('main');