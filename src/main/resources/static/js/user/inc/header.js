import CartRepository from "../../module/CartRepository.js";

export default class Header {
    #header
    #cartCircle
    constructor() {
        this.#header = document.querySelector("header");
        this.#cartCircle = this.#header.querySelector(".cart-circle");
    }

    // 장바구니에 담겨 있는 상품 수 갱신하기
    async renewCart() {

        // 장바구니에 담겨 있는 상품 개수 가져오기
        let cartRepository = new CartRepository();
        let count = await cartRepository.count();

        // 있다면 장바구니에 개수 표시
        if(count > 0){
            this.#cartCircle.classList.remove("d:none")
            this.#cartCircle.textContent = count;
        }
    }

}