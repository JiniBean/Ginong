const { createApp } = Vue


createApp({
    data() {
        return {
            coupon: {},
            couponCategory: [
                // { id: 1, name: '이벤트' }
            ],
            discountCategory: [
                { id: 1, name: '원' },
                { id: 2, name: '%' },
            ],
            showCouponDropdown: false,
            showUnitDropdown: false,

        }
    },
    methods:{
        goList() {
            location.href = `/admin/coupon/list`;
        },

        clickCouponDropdown() {
            this.showCouponDropdown = !this.showCouponDropdown;
        },

        clickUnitDropdown() {
            this.showUnitDropdown = !this.showUnitDropdown;
        },

        clickDropdownElement(c) {
            console.log(c);
            //this.selectedCategory = c;
            this.notice.categoryId = c.id;
            this.showDropdown = !this.showDropdown;
        },

        getCategoryName() {
            for (let item of this.couponCategory) {
                if (this.coupon.categoryId == item.id)
                    return item.name;
            }
            return '선택';
        },

    },
    async created() {
        let params = new URLSearchParams(location.search);
        console.log(params.get('couponId'));
        let couponId = params.get('couponId');

        let response = await fetch(`/api/coupons/${couponId}`);
        let coupon = await response.json();
        this.coupon = coupon;

        response = await fetch(`/api/coupons/coupon-category`);
        let category = await response.json();
        this.category = category;
    },
}).mount('main');


