const { createApp } = Vue


createApp({
    data() {
        return {            
            tabIndex: 0,
            showDropdown: false,
            list: [],
        }
    },
    methods:{
        // 탭바꾸기
        clickTab(selectedIndex) {
            this.tabIndex = selectedIndex;

            // tabIndex가 바뀌면 api를 새로 호출해야함
            if (this.tabIndex == 0) {
                // 주문상태가 주문완료 배송준비중 배송중 배송완료 구매확정 상태
                this.loadList();
            }
            else if (this.tabIndex == 1) {
                // 취소요청중 취소완료 상태
                this.loadCancelList();
            }
        },

        // 점 버튼 누르기
        clickDropdown(coupon) {
            // 특정 카드의 showDropdown이 true/false로 바뀌어야 함
            let dropdownStatus = !coupon.showDropdown;

            // 일단 모든 점 버튼 메뉴를 닫음
            for(let item of this.list) {
                item.showDropdown = false;
            }
            // coupon.showDropdown = !coupon.showDropdown;
            coupon.showDropdown = dropdownStatus;
        },

        goReg() {
            location.href = `/admin/coupon/reg`;
        },

        goUpdate() {
            location.href = `/admin/coupon/update`;
        },

        goDetail(coupon) {
            let couponId = coupon.id;
            location.href = `/admin/coupon/detail?couponId=${couponId}`;
        },

    },
    async created() {
        let response = await fetch(`/api/coupons`);
        let list = await response.json();
        this.list = list;
    },
}).mount('main');


