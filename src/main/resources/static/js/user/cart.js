const { createApp } = Vue;
import Repository from '/js/module/CartRepository.js';
import Header from './inc/header.js';
createApp({
    data(){
        return{
            total:0
        }
    },
    methods:{
        async deleteHandler(isAll) {
            let inputs;
            if(isAll)//전체 삭제인지 판별
                inputs = document.querySelectorAll("input[name='chkId']");
            else // 선택삭제라면 체크된 노드만
                inputs = document.querySelectorAll("input[name='chkId']:checked");

            //심어둔 상품 아이디 배열로 만들기
            let list = [];
            inputs.forEach(i => list.push(i.dataset.id));

            //상품아이디 리스트로 delete 요청
            let repository = new Repository;
            let vaild = await repository.delete(list);

            //삭제 성공시 header 및 리스트 바꾸기
            if(vaild){
                const header = new Header;
                header.renewCart();
                window.location.reload();
            }
        },
        async qtyHandler(e){
            let qtyDiv = e.target.closest('div');                               // 클릭한 상품 수량 증가 전체 영역
            let prdInput = qtyDiv.querySelector("input[name='prdId']");         // 상품 아이디 인풋(hidden)
            let qtyInput = qtyDiv.querySelector("input[name='quantity']");      // 상품 수량 인풋(hidden)
            let qtySpan = qtyDiv.querySelector("span[class='txt-al:center']");  // 상품 수량 영역(증감 버튼 사이 숫자)

            // 클릭한 상품의 아이디와 현재 수량 갖고 오기
            let qty = parseInt(qtyInput.value);
            let prdId = parseInt(prdInput.value);

            // 사용자가 누른 버튼이 플러스인지 아닌지 판별
            e.target.classList.contains('icon:plus') ? qty++ : qty--;
            if(!qty) //0 = falsy 따라서 0이면 더이상 진행 안함
                return;

            // 수량 변경하기
            qtySpan.textContent = qty;
            qtyInput.value = qty;

            // 데이터 베이스에 수량 업데이트하기
            let repository = new Repository;
            let s = await repository.updateQty(prdId, qty);

        }
    },

    mounted(){
        const costEl = document.querySelector(".prd");
        const dlvryEl = document.querySelector(".dlvry");

        let cost = parseInt(costEl.dataset.cost);
        let dlvry = parseInt(dlvryEl.dataset.dlvry);

        this.total = cost + dlvry;

    }

}).mount('main');

