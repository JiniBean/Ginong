const { createApp } = Vue

createApp({
    data() {
        return {
            notice: {
                title: '공지3',
                regDate: '2024-05-17',
                startDate: '2024-05-21',
                endDate: '2024-05-31',
                content: '공지 세번째',
                categoryId: '3',
            },
            category: [],
            showDropdown: false,
        }
    },
    computed: {
    },
    methods: {
        clickDropdown() {
            this.showDropdown = !this.showDropdown;
        },

        clickDropdownElement(c) {
            console.log(c);
            //this.selectedCategory = c;
            this.notice.categoryId = c.id;
            this.showDropdown = !this.showDropdown;
        },

        getCategoryName() {
            for (let item of this.category) {
                if (this.notice.categoryId == item.id)
                    return item.name;
            }
            return '선택';
        },
        
        goList() {
            location.href = `/admin/notice/list`;
        },
        
        async regNotice() {
            let requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ 
                    data:notice
                }),
            };

            console.log("요청", requestOptions);
            // await fetch(`/user/api/order/${orderId}`, requestOptions);
            //     // .then(response => response.json());
        },
    },
    async created() {
        response = await fetch(`/api/notices/notice-category`);
        let category = await response.json();
        this.category = category;
    }
}).mount('main');

