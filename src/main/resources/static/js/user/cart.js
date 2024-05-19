const {createApp} = Vue;
import Repository from '/js/module/CartRepository.js';
import Header from '/js/module/header.js';

createApp({
    data() {
        return {
              total             : 0
            , cartDataList      : []
            , dlvryPrice        : 0
            , isCartEmpty       : true
            , location          : []
        }
    },
    methods: {
        async deleteHandler(isAll) {

            let isTrue= confirm("정말로 삭제하시겠어요?");

            if(!isTrue)
                return;

            let inputs;

            if (isAll)//전체 삭제인지 판별
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
            if (vaild) {
                const header = new Header;
                header.renewCart();
                window.location.reload();
            }
        },
        async qtyHandler(item, amount) {

            // 장바구니 + 또는 - 클릭 한 만큼 수량 계산
            item.cartQuantity += amount;

            if (item.cartQuantity <= 0) {
                // 1 아래로 내려가지 못하도록 막기
                item.cartQuantity = 1;
            } else if (item.cartQuantity > item.stockQuantity) {
                // 주문 가능 수량 보다 많지 않도록 막기
                return item.cartQuantity = item.stockQuantity;
            }

            // 데이터 베이스에 수량 업데이트하기
            let repository = new Repository;
            let s = await repository.updateQty(item.productId, item.cartQuantity);

            this.cartDataHandler();

        },

        async cartDataHandler() {
            // DB에 저장되어 있는 장바구니 정보를 가져 옴.
            let repository = new Repository;

            //todo findAvailableQty() parameter로 memberId를 받지만, test 용도로 기본값을 줌.
            // 추후 parameter에 memberId값 처리 필요함.
            const reponseData = await repository.findAvailableQty();

            // DB로 부터 받은 정보를 data() => cartDataList 에 저장
            this.cartDataList = reponseData.map(item => ({
                productId: item.PRODUCT_ID,
                prettyName: item.PRETTY_PRODUCT_NAME,
                productPrice: item.PRODUCT_PRICE,
                cartQuantity: item.CART_QUANTITY,
                stockQuantity: item.STOCK_QUANTITY,
                stockStatus: item.STOCK_STATUS,
                discountRate : item.DISCOUNT_RATE,
                disCountPrice : item.DISCOUNT_PRICE
            }));

            const reponseData2 = await repository.findLocationByMemberId();
            this.location = reponseData2;
        },

        async emptyCartHandler() {
            let repository = new Repository;
            const reponseData = await repository.count();
            if(reponseData > 0) this.isCartEmpty = false;

        },

        submitHandler(e){
            if (this.isCartEmpty) {
                e.preventDefault();
                alert("장바구니가 비었어요!");
            }
        }

    },

    computed:{
        totalPrice() {
            // 상품 금액
            return this.cartDataList.reduce((sum, item) => {
                this.total = sum + (item.productPrice * item.cartQuantity);
                return this.total;
            }, 0);

            // reduce() 함수는 네 개의 인자를 가집니다.
            // 1. 누산기 (acc)
            // 2. 현재 값 (cur)
            // 3. 현재 인덱스 (idx)
            // 4. 원본 배열 (src)

            // 누산기 인자(여기선 sum)에 item의 계산 결과를 누적해서 return 시키므로,
            // 배열에 여러 값이 있을 경우 총 합산 값을 구하는데 자주 사용하는 듯 합니다.
            // 참고 => https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/reduce

            // 따라서 sum 이라는 인자에 item의 계산 결과를 계속 누적하여 합산할 수 있는 함수 입니다.(item = cartDataList)
            // item.productPrice * item.cartQuantity의 값을 계속 누적시켜 최종 합을 구하는 함수 입니다.
        },

        finalPrice(){
            // 최종 결제 금액
            return this.total + this.dlvryPrice;
        },


    },

    mounted() {
        // DB에 저장되어 있는 장바구니 정보를 가져 오기.
        this.cartDataHandler();

        this.emptyCartHandler();
    }

}).mount('main');

