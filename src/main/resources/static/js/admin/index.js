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
            location.href = `admin/stock/detail?p=${id}`;
        },
        moveInquiry(){

        },
        async getOrderStats(){
            let res = await fetch('/api/stats/order');
            this.orderStats = await res.json();
        },
        async getStockStats(){
            let res = await fetch('/api/stats/stock');
            this.stockStats = await res.json();
        },
        async getInquiryStats(){
            let res = await fetch('/api/stats/inquiry');
            this.inquiryStats = await res.json();
        }
    },
    mounted(){
        this.getOrderStats();

        this.getStockStats();

        console.log(this.orderStats.toString())
        console.log(this.stockStats.toString())
    }
}).mount('main');