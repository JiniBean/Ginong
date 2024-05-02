const { createApp } = Vue

// const dummyResponse = {
//     orderId: 2024042490646637,
//     orderDate: '2024.04.25',
//     orderStatus: 3,
//     orderItems: [
//         { id: 1, productName: '텃밭 흙 감자', productPrice: 4500, quantity: 1 },
//         { id: 2, productName: '직접 구해서 만든 어리굴젓 무침', productPrice: 58200, quantity: 1 },
//         { id: 3, productName: '진짜 맛있는 오이고추장무침', productPrice: 9000, quantity: 1 }
//     ],
//     location: {
//         id: 1, receiverName: '기농이', receiverPhone: '010-1234-1234', addr1: '서울시 마포구 백범로 3', addr2: '3층 하이미디어'
//     },
//     payment: {
//         id: 1, deliveryFee: 2500, totalAmt: 26900, point: 1000, coupon: 1000
//     },
//     orderState: [
//         { id: 1, name: '주문완료'},
//         { id: 2, name: '배송준비중'},
//         { id: 3, name: '배송중'},
//         { id: 4, name: '배송완료'},
//     ],

// }

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
            checkbox.forEach(item => item.checked = checked)
        }
    },
    async created(){
    
        // this.order = dummyResponse
        // this.order = { ...dummyResponse }
        let params = new URLSearchParams(location.search);
        console.log(params.get('orderId'));
        let orderId = params.get('orderId');

        // let response = await fetch("/user/api/order/detail/items");
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

        response = await fetch("/user/api/order/status");
        let state = await response.json();
        this.state = state;
    },
}).mount('main');


