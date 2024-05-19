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

        clickCouponDropdownElement(c) {
            console.log(c);
            //this.selectedCategory = c;
            this.coupon.categoryId = c.id;
            this.showCouponDropdown = !this.showCouponDropdown;
        },

        clickUnitDropdownElement(c) {
            console.log(c);
            //this.selectedCategory = c;
            this.coupon.discountUnit = c.name;
            this.showUnitDropdown = !this.showUnitDropdown;
        },

        getCouponCategoryName() {
            for (let item of this.couponCategory) {
                if (this.coupon.couponCategoryId == item.id)
                    return item.name;
            }
            return '선택';
        },

        getUnitCategoryName() {
            for (let item of this.discountCategory) {
                if (this.coupon.discountUnit == item.name)
                    return item.name;
            }
            return '선택';
        },

    },
    async created() {
        let params = new URLSearchParams(location.search);
        let couponId = params.get('couponId');

        let response = await fetch(`/api/coupons/${couponId}`);
        let coupon = await response.json();
        console.log(coupon);
        this.coupon = coupon;

        response = await fetch(`/api/coupons/coupon-category`);
        let category = await response.json();
        this.couponCategory = category;
    }
}).mount('main');


