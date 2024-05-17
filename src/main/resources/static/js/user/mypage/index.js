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
    async created(){
        response = await fetch(`/user/api/order/${orderId}/orderInfo`);
        let info = await response.json();
        this.info = info;

    },
}).mount('main');