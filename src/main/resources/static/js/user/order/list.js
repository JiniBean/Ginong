const { createApp } = Vue

createApp({
    data() {
        return {
            list: [],
            itemList: [],
            //info: [],
            state: [],
            tabIndex: 0,
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

        // 주문 상세내역 페이지로 이동하기
        goDetail(order) {
            let orderId = order.id;
            location.href = `/order/detail?orderId=${orderId}`;
        },

        async confirmOrder(order) {
            console.log(order);
            let orderId = order.id;
            let requestOptions = {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: 5
            };
            await fetch(`/user/api/order/${orderId}`, requestOptions)
                // .then(response => response.json());
            this.loadList();
        },

        async loadList() {
            let memberId = 40;
            let response = await fetch(`/user/api/order/${memberId}/list`);
            let list = await response.json();
            this.list = list;

            for (item of list) {
                const dateIdx = item.date.search("T");
                const subDate = item.date.substring(0, dateIdx);
                item.date = subDate;
            }

            let orderId = list && list[0] && list[0].id;
            response = await fetch(`/user/api/order/${orderId}/items`)
            this.itemList = await response.json();

            // 페이지에 있는 order id들을 전부 가져옴
            // const ids = this.list.map(order => order.id);
            // console.log(ids);
            // response = await fetch(`/user/api/order/items?ids=${ids}`);
            // let itemsMap = await response.json();
            // this.itemList = itemList;
            // console.log(itemList);
        },
        async loadCancelList() {
            let memberId = 40;
            let response = await fetch(`/user/api/order/${memberId}/canceledList`);
            let list = await response.json();
            this.list = list;

            for (item of list) {
                const dateIdx = item.date.search("T");
                const subDate = item.date.substring(0, dateIdx);
                item.date = subDate;
            }

            let orderId = list && list[0] && list[0].id;
            response = await fetch(`/user/api/order/${orderId}/items`)
            this.itemList = await response.json();

            //TODO: 하나의 주문아이디에 딸린 여러개의 아이템을 어떻게 각각 받아오는가?
            // const ids = this.list.map(order => order.id);
            // console.log(ids);
            // response = await fetch(`/user/api/order/items?ids=${ids}`);
            // let itemsMap = await response.json();
            // this.itemList = itemList;
        }
    },
    created(){
        this.loadList();

        // response = await fetch("/user/api/order/detail/status");
        // let state = await response.json();
        // this.state = state;
    },
}).mount('main');


