const { createApp } = Vue

createApp({
    data() {
        return {
            order: {},
            itemList: [],
            address: [],
            member: [],
            info: [],
            state: [],
        }
    },
    methods:{
        // 전체선택
        checkAll(e) {
            let checked = e.target.checked
            // this.order.orderItems.forEach(item => item.checked = checked)
            this.itemList.forEach(itemList => itemList.checked = checked)
        }
    },
    async created(){
        let params = new URLSearchParams(location.search);
        console.log(params.get('orderId'));
        let orderId = params.get('orderId');

        let response = await fetch(`/user/api/order/${orderId}/items`);
        let itemList = await response.json();
        this.itemList = itemList;

        response = await fetch(`/user/api/order/${orderId}/location`);
        let address = await response.json();
        this.address = address;

        response = await fetch(`/user/api/order/${orderId}/member`);
        let member = await response.json();
        this.member = member;

        response = await fetch(`/user/api/order/${orderId}/orderInfo`);
        let info = await response.json();
        this.info = info;

        const dateIdx = info.orderDate.search("T");
        const subDate = info.orderDate.substring(0, dateIdx);
        this.info.orderDate = subDate;

        response = await fetch("/user/api/order/delivery-status");
        let state = await response.json();
        this.state = state;
    },
}).mount('main');


