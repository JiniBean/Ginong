const { createApp } = Vue

createApp({
    data() {
        return {
            notice: {},
            category: [],
            showDropdown: false,
            // selectedCategory: {
            //     id: '',
            //     name: '선택',
            // }
        }
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
    },
    async created() {
        let params = new URLSearchParams(location.search);
        let noticeId = params.get('noticeId');

        let response = await fetch(`/api/notices/${noticeId}`);
        let notice = await response.json();
        this.notice = notice;

        response = await fetch(`/api/notices/notice-category`);
        let category = await response.json();
        this.category = category;
    }
}).mount('main');

