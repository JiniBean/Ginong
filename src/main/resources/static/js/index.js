const { createApp } = Vue

createApp({
    data() {
        return {
            best: false,
            sale: false,
            pick: false,
        }
    },
    methods: {
        selectItem(item) {
            // 해당 항목을 클릭하면 해당 항목의 상태를 true로 설정하고 다른 항목의 상태를 false로 설정.
            this[item] = true;
            for (let key in this.$data) {
                if (key !== item) {
                    this[key] = false;
                }
            }
        },

    },
}).mount('main')



