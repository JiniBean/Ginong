const { createApp } = Vue


createApp({
    data() {
        return {
            list: [],
            itemList: [],
            info: [],
            state: [],
        }
    },
    methods:{
        // 탭바꾸기
        clickTab(e) {
            console.log("여긴 어디?", e.target.innerText);
            const targetElement = this.$refs.statusElement;

            if(e.target.tagName != 'A')
                return;

            e.preventDefault();

            if(e.target.innerText == '주문 내역') {
                console.log("구매내역!!!!!!!");

                console.log(targetElement.innerText);
            }
                
            else {
                console.log("취소내역!!!!!!!");
            }
        },

        
    },
    async created(){
    
        let response = await fetch("/user/api/order/list");
        let list = await response.json();
        this.list = list;

        response = await fetch("/user/api/order/detail/items");
        let itemList = await response.json();
        this.itemList = itemList;

        // response = await fetch("/user/api/order/detail/orderInfo");
        // let info = await response.json();
        // this.info = info;

        // response = await fetch("/user/api/order/detail/status");
        // let state = await response.json();
        // this.state = state;
    },
}).mount('main');


