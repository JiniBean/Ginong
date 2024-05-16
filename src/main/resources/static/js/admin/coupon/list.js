const { createApp } = Vue


createApp({
    data() {
        return {            
            tabIndex: 0,
            showDropdown: false,
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
        clickDropdown() {
            this.showDropdown = !this.showDropdown;
        }

    },
    async created() {
        
    },
}).mount('main');


