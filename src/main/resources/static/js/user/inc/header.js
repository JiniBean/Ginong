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

    // 검색창 script, 상품만 검색 가능
    searchBar() {
        const searchIcon = this.#header.querySelector('.search-icon');
        const searchInput = this.#header.querySelector('.search-input');
        const searchForm = this.#header.querySelector('form');

        searchIcon.addEventListener('click', function(e) {
            e.preventDefault(); // 기본 이벤트 방지
            if (searchInput.classList.contains('show') && searchInput.value.trim() !== "") {
                // 검색창이 화면에 출력되지 않은 상태 + 검색어가 검색어가 한 글자라도 포함되어 있는 경우
                searchForm.submit(); // 검색창에 내용이 있으면 폼 제출
            } else {
                searchInput.classList.toggle('show'); // 검색창에 내용이 없으면 표시 상태 토글

                if (searchInput.classList.contains('show')) {
                    searchInput.focus(); // 검색창 화면에 표시 시 포커스
                }
            }
        });
    }





}