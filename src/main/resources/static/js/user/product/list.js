function Cookie() {
    this.map = {};

    if (document.cookie) {
        let cookieDecoded = decodeURIComponent(document.cookie);
        let tokens = cookieDecoded.split(";");

        for (let c of tokens) {
            let tmp = c.split("=");
            let key = tmp[0];
            let value = tmp[1];

            this.map[key] = value;
        }
    }

}

Cookie.prototype = {
    get: function (name) {
        return this.map[name];
    },

    save: function () {
        // document.cookie = "menus=hh; path=/;";
        let list = this.map["menus"];
        let size = list.length;
        let lastIdx = size - 1;

        let str = "[";

        for (m of list) {
            str += JSON.stringify(m);
            if (m !== list[lastIdx]) str += ",";
        }

        str = "]";
        let encoded = encodeURIComponent(str);
        document.cookie = `menus=${encoded}; path=/;`;

    },

    remove: function (name) {

    },

    add: function (name, value) {

    },

    addItem: function (name, item) {
        let list = this.map[name];
        list.push(item);
    },

    set: function (name, value) {
        document.cookie = name + "=" + value + "; path=/";
    }

}
// ========== 장바구니 담기 =========================================================
import CartRepository from "../../module/CartRepository.js";
document.addEventListener('click', async function (e) {

    const cartBox = e.target.closest(".cart-box");

    if (cartBox) {
        const prdId = cartBox.dataset.id;
        e.preventDefault();

        let cartRepository = new CartRepository();
        let item = await cartRepository.findItem(prdId);
        let vaild = false;
        if(item == null)
            vaild = await cartRepository.add(prdId);
        else
            vaild = await cartRepository.addCount(prdId);

        if(vaild){
            item  = await cartRepository.findItem(prdId);
            let qty = item.quantity;
            console.log(item);
            cartBox.textContent = qty;
            cartBox.classList.add('bg-color:main-6');
            cartBox.classList.add('color:base-1');

        }




    }


});


// ========== 정렬 =========================================================

window.addEventListener("load", function () {

    const sortSection = document.getElementById("sort");
    const prdList = document.getElementById("prd-list");
    const pagerSection = document.getElementById("pager");

    const colBtn = sortSection.querySelector(".icon\\:squares_four"); //모바일 버전 세로 정렬 버튼
    const rowBtn = sortSection.querySelector(".icon\\:list_bullets"); //모바일 버전 가로 정렬 버튼
    const colSection = prdList.querySelector(".menu-card-col"); //세로형 카드 섹션
    const rowSection = prdList.querySelector(".menu-card-row"); //가로형 카드 섹션
    const pcSection  = document.querySelector(".prd-list-pc"); //PC 카드 섹션

    const alignNumberBox = sortSection.querySelector(".align-number");        // 데이터 표시 갯수 체크

    let pagerButtons = pagerSection.querySelectorAll("li a");

    // URLSearchParams를 사용하여 현재 URL의 파라미터 가져오기
    let params = new URLSearchParams(window.location.search);
    // 현재 페이지의 도메인, 포트를 가져오기. (application.yaml에서 port변경하는 경우를 위함)
    let baseUrl = window.location.origin;

    // 특정 파라미터 값 가져오기
    let c = params.get('c');


    const priceBtn = sortSection.querySelector(".price"); //가격순
    const recommendBtn = sortSection.querySelector(".recommend"); //추천순

    const content = prdList.getElementsByClassName("content");
    

    // 이전에 선택한 버튼 상태를 쿠키에서 불러옴
    let cookie = new Cookie();
    let previousBtn = cookie.get("previousBtn");

    if (previousBtn === "colBtn")
        col();
    else if (previousBtn === "rowBtn")
        row();

    // 세로형 상품 카드로 바꾸기
    colBtn.onclick = function (e) {
        col();
        cookie.set("previousBtn", "colBtn");
        e.preventDefault();
    }

    // 가로형 상품 카드로 바꾸기
    rowBtn.onclick = function (e) {
        row();
        cookie.set("previousBtn", "rowBtn");
        e.preventDefault();
    }

    function col() {
        rowBtn.classList.remove("d:none");
        colSection.classList.remove("d:none");

        colBtn.classList.add("d:none");
        rowSection.classList.add("d:none");
    }

    function row() {
        colBtn.classList.remove("d:none");
        rowSection.classList.remove("d:none");

        rowBtn.classList.add("d:none");
        colSection.classList.add("d:none");
    }


    priceBtn.onclick = function (e) {
        e.preventDefault();

        priceBtn.classList.add("color:main-6");
        recommendBtn.classList.remove("color:main-6");

        let sortType = 1;
        let url = `${baseUrl}/user/api/product?p=1&s=${sortType}&c=${c}`;

        request(url, function (list) {
            bind(list);
        });
    }

    recommendBtn.onclick = function (e) {
        e.preventDefault();

        priceBtn.classList.remove("color:main-6");
        recommendBtn.classList.add("color:main-6");
        let sortType = 2;
        let url = `${baseUrl}/user/api/product?p=1&s=${sortType}&c=${c}`;

        request(url, function (list) {
            bind(list);
            console.log("추천순 정렬 리로드");
        });
    }

    /* 상품 display 갯수 선택, default 20*/
    alignNumberBox.onchange = function(e) {
        e.stopPropagation();
        e.preventDefault();

        let alignNumber = alignNumberBox.value;

        let url = `${baseUrl}/user/api/product?p=1&c=${c}&r=${alignNumber}`;
        console.log(url, alignNumber);

        request(url, function (list) {
            bind(list);
            updatePagerLink(alignNumber);
            console.log(`${alignNumber}개씩 출력`);
        });
    }

    function updatePagerLink(size) {
        // console.log(pagerButtons);
        for(let a of pagerButtons) {
            // https://developer.mozilla.org/en-US/docs/Web/API/URLSearchParams
            let parts = a.href.split('?'); // ['/list', 'p=1&r=1']
            let params = new URLSearchParams(parts[1] || '');
            params.set('r', size);
            a.href = `${parts[0]}?${params.toString()}`;
            // console.log(a.href, a);
        }
    }
    


    // 가격,추천순 정렬 작업 중 ...
    function request(url, callback, method) {

        method = method || "GET";

        let xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.onload = function () {
            let list = JSON.parse(this.responseText);
            callback(list);
        };

        xhr.open(method, url);
        xhr.send();
    }

    function bind(list) {
        rowSection.innerHTML = "";
        colSection.innerHTML = "";
        pcSection.innerHTML = "";
        
        // 가로형 카드 렌더링
        for (let m of list) {

            let rowSectiondHTML = `
<!--                <div class="menu-card-row">-->
                <div>
                    <div class="d:flex w:10p gap:3">
                        <div class="pos:relative">
                            <a href="/user/product/detail?id=${m.id}">
                                <img src="${m.thumbnailPath}/Meongmeong.jpg" height="110px" width="110px" alt="상품 이미지" class="bd-radius:4"/>
                            </a>
                            <div class="cart-section">
                                <div>
                                    <a href="" class="icon-shopping_cart icon icon:shopping_cart_simple icon-color:base-1 icon-size:4">장바구니아이콘 </a>
                                </div>
                            </div>
                        </div>
                        <div class="d:flex fl-dir:column jc:center gap:3">
                            <div class="d:flex fl-dir:column jc:center gap:1">
                                <a href="/user/product/detail?id=${m.id}">
                                    <span>${m.name}</span>
                                    <span>, </span>
                                    <span>${m.quantity}</span>
                                    <span>${m.quantityCategory}</span>
                                    <span>(</span>
                                    <span>${m.weight}</span>
                                    <span>${m.weightCategory}</span>
                                    <span>)</span>
                                </a>
                                <a href="">
                                    <span class="fw:3">${m.price}</span>
                                    <span class="fw:3">원</span>
                                </a>
                            </div>
                            <div class="deco icon:smile deco-color:sub-4 deco-size:1">${m.likeCount}</div>
                        </div>
                    </div>
                </div>`;

            rowSection.insertAdjacentHTML("beforeend", rowSectiondHTML);
        }
        
        // 세로형 카드 렌더링
        for (let m of list) {

            let colSectionHTML = `
<!--                <div class="menu-card-col">-->
                <div>
                    <div class="d:flex fl-dir:column gap:3" style="width: 180px; height: 294px">
                        <div class="pos:relative">
                            <a href="/user/product/detail?id=${m.id}" class="">
                                <img src="${m.thumbnailPath}/Meongmeong.jpg" height="180px" width="180px" alt="상품 이미지" class="bd-radius:2"/>
                            </a>
                            <div class="cart-section">
                                <div>
                                    <a href="" class="icon-shopping_cart icon icon:shopping_cart_simple icon-color:base-1 icon-size:4">장바구니아이콘</a>
                                </div>
                            </div>
                        </div>
                        <div class="d:flex fl-dir:column jc:center gap:2">
                            <div class="d:flex fl-dir:column jc:center gap:1">
                                <a href="/user/product/detail?id=${m.id}" style="height: 34px;">
                                    <span>${m.name}</span>
                                    <span>, </span>
                                    <span>${m.quantity}</span>
                                    <span>${m.quantityCategory}</span>
                                    <span>(</span>
                                    <span>${m.weight}</span>
                                    <span>${m.weightCategory}</span>
                                    <span>)</span>
                                </a>
                                <a href="/user/product/detail?id=${m.id}">
                                
                                    <span class="fw:3">${m.price}</span>
                                    <span class="fw:3">원</span>
                                </a>
                            </div>
                            <div class="deco icon:smile deco-color:sub-4 deco-size:1">${m.likeCount}</div>
                        </div>
                    </div>
                </div>`;

            colSection.insertAdjacentHTML("beforeend", colSectionHTML);
        }
        
        // PC버전 카드 렌더링
        for (let m of list) {

            let pcSectiondHTML = `
<!--                <div class="menu-card-row">-->
                <section class="font-size:3" style="width:280px; height:404px">
                    <h1 class="d:none">상품 영역</h1>
                    <div class="d:flex fl-dir:column w:10p h:10p jc:space-between">
                        <div class="pos:relative">
                            <a href="/user/product/detail?id=${m.id}" class=""><img src="${m.thumbnailPath}/Meongmeong.jpg" height="280px" width="280px" alt="상품 이미지" class="bd-radius:2"/></a>
                            <div class="cart-section">
                                <div>
                                    <a href="" class="icon-shopping_cart icon icon:shopping_cart_simple icon-color:base-1 icon-size:4 color:base-1">장바구니 아이콘</a>
                                </div>
                            </div>
                        </div>
                        <div class="d:flex fl-dir:column jc:center gap:4">
                            <div class="d:flex fl-dir:column jc:center gap:2">
                                <a href="/user/product/detail?id=${m.id}" class="h:2" >
                                    <span>${m.name}</span>
                                    <span>, </span>
                                    <span>${m.quantity}</span>
                                    <span>${m.quantityCategory}</span>
                                    <span>(</span>
                                    <span>${m.weight}</span>
                                    <span>${m.weightCategory}</span>
                                    <span>)</span>
                                </a>
                                <a href="/user/product/detail?id=${m.id}">
                                    <span class="fw:3">${m.price}</span>
                                    <span>원</span>
                                </a>
                            </div>
                            <div class="deco icon:smile deco-color:sub-4 deco-size:4">${m.likeCount}</div>
                        </div>
                    </div>
                </section>`;

            pcSection.insertAdjacentHTML("beforeend", pcSectiondHTML);
        }


    }


});

