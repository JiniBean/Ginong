const { createApp } = Vue
const dummyRes = {
    orderId: 123123,
    orderData: '2024.04.25',
    orderState: 1,
    orderItems: [
        { id: 1, productName: '시바 당근', price: 1000, quantity: 1 },
        { id: 2, productName: '쭤까 당근', price: 1000, quantity: 1 },
        { id: 3, productName: '쭤뻐 당근', price: 1000, quantity: 1 }
    ],
    location: {
        // ...
    },
    payment: {
        // ...
    }
}

createApp({
    data() {
        return {
            query:"",
            order: {},
            list:[]
        }
    },
    methods:{
        queryClickHandler() {
            this.list.push({});
        },
        checkAll(e) {
            let checked = e.target.checked
            this.order.orderItems.forEach(item => item.checked = checked)
        }
    },
    async created(){
        console.log("created");
        // let response = await fetch("/user/api/order/detail");
        // let list = await response.json();
        // this.list = list;
        this.order = dummyRes
    },
    // beforeCreate(){},
    // beforeMount(){},
    // mounted(){},
    // beforeUpdate(){},
    // updated(){},
    // beforeUnmount(){},
    // unmounted(){},
    // activated(){},
    // deactivated(){},
}).mount('main');


