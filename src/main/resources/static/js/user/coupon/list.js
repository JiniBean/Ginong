const { createApp } = Vue

createApp({
    data() {
        return {            
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


        // 주문내역(배송요청중~구매확정)
        async loadList() {
            // let memberId = 40;
            // let response = await fetch(`/user/api/order/${memberId}/list`);
            // let list = await response.json();
            // this.list = list;

            // for (item of list) {
            //     const dateIdx = item.date.search("T");
            //     const subDate = item.date.substring(0, dateIdx);
            //     item.date = subDate;
            // }

            // const ids = this.list.map(order => order.id);
            // response = await fetch(`/user/api/order/items?ids=${ids}`);
            // let itemsMap = await response.json();
            // this.itemsMap = itemsMap;

        },

        // 주문취소내역(취소요청중, 취소완료)
        async loadCancelList() {
            // let memberId = 40;
            // let response = await fetch(`/user/api/order/${memberId}/canceledList`);
            // let list = await response.json();
            // this.list = list;

            // for (item of list) {
            //     const dateIdx = item.date.search("T");
            //     const subDate = item.date.substring(0, dateIdx);
            //     item.date = subDate;
            // }

            // const ids = this.list.map(order => order.id);
            // response = await fetch(`/user/api/order/items?ids=${ids}`);
            // let itemsMap = await response.json();
            // this.itemsMap = itemsMap;

        },
    },
    async created(){
        // this.loadList();

        // response = await fetch("/user/api/order/status");
        // let state = await response.json();

        // list를 object(map)으로 변경한다. {id, name}
        // https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/forEach
        // let obj = {};

        // state.forEach((o, i) => {
        //     console.log(i, '++++', o);
        //     obj[o.id] = o.name;
        // });

        // this.state = obj;
    },
}).mount('main');


window.addEventListener("load", function () {
    const dropdownButton = document.getElementById("dropdown-btn");
    const dropdownList = document.getElementById("dropdown-list");
  
    dropdownButton.addEventListener("click", function () {
      dropdownList.classList.toggle("active");
    });
});