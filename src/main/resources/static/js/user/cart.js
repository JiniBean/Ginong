const {createApp} = Vue;
import Repository from '/js/module/CartRepository.js';
import ProductRepository from '/js/module/ProductRepository.js';
import Header from '/js/module/header.js';

createApp({
    data() {
        return {
            total         : 0
            , cartDataList: []
            , dlvryPrice  : 0
            , location    : []
            , isLogin     : false
        }
    },
    methods: {
        async deleteHandler(isAll) {

            let isTrue = confirm("정말로 삭제하시겠어요?");

            if (!isTrue)
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
            let header = new Header;
            this.isLogin = header.checkUser();
            let repository = new Repository;
            let productRepository = new ProductRepository;

            // 로그인 여부에 따라 DB 또는 쿠키에서 장바구니 데이터 가져오기
            let temp;                                                           // 임시 변수
            if (this.isLogin) {
                // 로그인 => DB에서 데이터 추출
                temp = await repository.findAvailableQty();                     // DB -> 임시변수 데이터 담기
                this.cartDataList = temp.map(item => ({                         // 임시변수 ->  cartDataList(화면과 바인딩된 변수) 담기
                    productId    : item.PRODUCT_ID,
                    prettyName   : item.PRETTY_PRODUCT_NAME,
                    productPrice : item.PRODUCT_PRICE,
                    cartQuantity : item.CART_QUANTITY,
                    stockQuantity: item.STOCK_QUANTITY,
                    stockStatus  : item.STOCK_STATUS,
                    discountRate : item.DISCOUNT_RATE,
                    disCountPrice: item.DISCOUNT_PRICE
                }));
            } else {
                // 비-로그인 => 쿠키에서 상품 ID 출력하여 해당 상품 정보 출력
                // 비회원의 경우 쿠키에서 장바구니 정보를 가져오는데, 쿠키에는 상품ID와 개수만 존재 합니다.
                // 따라서, view단에 정보를 출력하기 위해서는 상품ID에 대한 정보(이름, 가격 등..)를 DB에서 가져온 뒤. 쿠키의 정보와 합쳐서 사용하기 위한 코드 입니다.

                let temp = this.getCartListFromCookies();                                      // 쿠키->임시변수 데이터 담기
                let ids = temp.map(item => item.prdId);                                        // 쿠키에서 id 값만 꺼내기
                let products = await productRepository.findAllCartItem(ids);                   // 쿠키에서 꺼낸 id 값을 이용해서 상품 정보 가져오기
                this.cartDataList = products.map(product => {
                    let foundItem = temp.find(item => item.prdId === product.PRODUCT_ID);      // 임시변수 id == 상품정보 id 찾기
                    let stockStatus = (product.STOCK_QUANTITY - (foundItem ? foundItem.quantity : 0)) > 0 ? 'valid' : 'invalid'; // (현재고량 - 장바구니 담은 수) > 0 ? vaild : invaild --> 상품주문가능여부
                    return {
                        productId    : product.PRODUCT_ID,
                        prettyName   : product.PRETTY_PRODUCT_NAME,
                        productPrice : product.PRODUCT_PRICE,
                        cartQuantity : foundItem.quantity,                                     // 비회원이 장바구니에 담은 수량 넣기
                        stockQuantity: product.STOCK_QUANTITY,
                        stockStatus  : stockStatus,
                        discountRate : product.DISCOUNT_RATE,
                        disCountPrice: product.DISCOUNT_PRICE
                    };
                });


            }


            const locationData = await repository.findLocationByMemberId();
            console.log("###############################################");
            console.log(locationData);
            console.log("###############################################");
            this.location = locationData;
        },

        async emptyCartHandler() {
            let repository = new Repository;
            const reponseData = await repository.count();
            if (reponseData > 0) this.isCartEmpty = false;

        },

        submitHandler(e) {
            if (this.isCartEmpty) {
                e.preventDefault();
                alert("장바구니가 비었어요!");
            }
        },

        // 쿠키에서 'cartList' 데이터를 읽어와 파싱하는 메소드
        getCartListFromCookies() {
            const cookies = decodeURIComponent(document.cookie).split('; ');
            const cartCookie = cookies.find(cookie => cookie.startsWith('cartList='));
            if (cartCookie) {
                return JSON.parse(cartCookie.split('=')[1]);
            }
            return [];
        }


    },

    computed: {
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

            // 따라서 sum 이라는 인자에 item의 계산 결과를 계속 누적하여 합산할 수 있는 함수 입니다.(item = cart)
            // item.productPrice * item.cartQuantity의 값을 계속 누적시켜 최종 합을 구하는 함수 입니다.
        },

        finalPrice() {
            // 최종 결제 금액
            return this.total + this.dlvryPrice;
        },

        isCartEmpty() {
            return this.cartDataList.length === 0;
        }

    },

    mounted() {
        let header = new Header;
        this.isLogin = header.checkUser();
        this.cartDataHandler();
    }

}).mount('main');

