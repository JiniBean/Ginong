const { createApp } = Vue;
createApp({
    data(){
        return{
            orderStats:[],
            stockStats:[],
            inquiryStats:[],
            inquiryList:[],
            baseUrl:window.location.origin,
            list : [],
            query: ""
        }
    },
    methods:{
        moveStock(id){
            //클릭한 상품의 상품 id를 인자로 받음
            location.href = `/stock/detail?p=${id}`;
        },
        moveInquiry(){

        },
        async getOrderStats(){
            let res = await fetch('/api/stats/order');
            this.orderStats = await res.json();
            console.log(this.orderStats);
        },
        async getStockStats(){
            let res = await fetch('/api/stats/stock');
            this.orderStats = await res.json();
            console.log(this.orderStats);
        },
        async getInquiryStats(){
            let res = await fetch('/api/stats/inquiry');
            this.orderStats = await res.json();
            console.log(this.orderStats);
        }
    },
    mounted(){
        this.getOrderStats();
        console.log(this.orderStats);
    }
}).mount('main');