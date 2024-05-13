const { createApp } = Vue

createApp({
    data() {
        return {
            list: [],
        }
    },
    method: {

    },
    async created() {
        let response = await fetch(`/api/notices`);
        let list = await response.json();
        this.list = list;
    }
}).mount('main');