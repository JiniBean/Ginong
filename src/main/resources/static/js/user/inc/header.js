import CartRepository from "../../module/CartRepository.js";

export default class Header {
    #header
    #cartCircle
    #user

    constructor() {
        this.#header = document.querySelector("header");
        this.#cartCircle = this.#header.querySelector(".cart-circle");
        this.#user = this.#header.querySelector(".user");
    }

    // 장바구니에 담겨 있는 상품 수 갱신하기
    async renewCart() {
        let count;

        if(this.#user){
            // DB에 담겨 있는 상품 개수 가져오기
            let cartRepository = new CartRepository();
            count = await cartRepository.count();
        }
        else {

        }


        // 있다면 장바구니에 개수 표시
        if(count > 0){
            this.#cartCircle.classList.remove("d:none")
            this.#cartCircle.textContent = count;
        }
    }

    //로그인 되어있는지 판별하기
    checkUser(){
        if(this.#user)
            return this.#user.textContent
        return false;
    }

}