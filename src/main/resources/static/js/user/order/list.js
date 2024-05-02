const { createApp } = Vue

createApp({
    data() {
        return {
            list: [],
            itemList: [],
            //info: [],
            //state: [],
            tabIndex: 0,
        }
    },
    methods:{
        // 탭바꾸기
        clickTab(selectedIndex) {

            this.tabIndex = selectedIndex;
            console.log("여긴 어디?", selectedIndex);
            console.log("Tab index!", this.tabIndex);

            // tabIndex가 바뀌면 api를 새로 호출해야함
            if (this.tabIndex == 0) {
                // 주문상태가 주문완료 배송준비중 배송중 배송완료 구매확정 상태
                this.loadList();
            }
            else if (this.tabIndex == 1) {
                // 취소요청중 취소완료 상태
                this.loadList();
            }

        },

        // 주문 상세내역 페이지로 이동하기
        goDetail(order) {
            let orderId = order.id;
            location.href = `/order/detail?orderId=${orderId}`;
        },

        // todo: 구매확정하기
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
        // todo: 주문내역 카드 api 다시 만들기
        async loadList() {
            let memberId = 40;
            let response = await fetch(`/user/api/order/${memberId}`);
            let list = await response.json();
            this.list = list;

            let orderId = list && list[0] && list[0].id;
            response = await fetch(`/user/api/order/${orderId}/items`)
            this.itemList = await response.json();

            // const ids = this.list.map(order => order.id);
            // console.log(ids);
            // response = await fetch(`/user/api/order/items?ids=${ids}`);
            // let itemsMap = await response.json();
            // itemsMap['10293012930193'] = [...]
            // this.itemList = itemList;
        }
    },
    created(){
        this.loadList();

        // response = await fetch("/user/api/order/detail/orderInfo");
        // let info = await response.json();
        // this.info = info;

        // response = await fetch("/user/api/order/detail/status");
        // let state = await response.json();
        // this.state = state;
    },
}).mount('main');


