const { createApp } = Vue;
import Repository from "/js/module/OrderRepository.js";
createApp({
    data(){
        return{
            orderType:1,//기본값
            sortType:null,
            list:[],
            query: "",
            category:{
                1:'주문',
                2:'교환',
                3:'환불',
                4:'취소'
            },
            code:{
                1:'요청',
                2:'진행중',
                3:'완료',
                4:'거절'
            }
        }
    },
    methods:{
        async getList(orderType, sortType){
            this.orderType = orderType;
            this.sortType = sortType
            let repository = new Repository;

            switch (orderType) {
                case 1:
                    this.list = await repository.findAll(this.query,sortType,false);
                    break;
                case 2:
                    this.list = await repository.findExRef(this.query,sortType,false,1);
                    break;
                case 3:
                    this.list = await repository.findExRef(this.query,sortType,false,2)
                    break;
                case 4:
                    this.list = await repository.findCancel(this.query,false)

            }

        }
    },
    mounted(){
        this.getList(1);
    }
}).mount('main');