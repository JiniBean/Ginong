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
            let vaild = await repository.delete(JSON.stringify(list));

            //삭제 성공시 header 및 리스트 바꾸기
            if(vaild){
                const header = new Header;
                header.renewCart();
                window.location.reload();
            }
        },
        async qtyHandler(e){
            let qtyDiv = e.target.closest('div')
            let prdInput = qtyDiv.querySelector("input[name='prdId']");
            let qtySpan = qtyDiv.querySelector("span[class='txt-al:center']");
            let qtyInput = qtyDiv.querySelector("input[name='quantity']");

            let qty = parseInt(qtyInput.value);
            let prdId = parseInt(prdInput.value);

            e.target.classList.contains('btn-plus') ? qty++ : qty--;
            if(!qty)
                return;

            qtySpan.textContent = qty;
            qtyInput.value = qty;

            let repository = new Repository;
            let s = await repository.updateQty(prdId, qty);
            console.log(s);

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

