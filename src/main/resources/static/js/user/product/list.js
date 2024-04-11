function Cookie() {
    this.map = {};

    if (document.cookie) {
        var cookieDecoded = decodeURIComponent(document.cookie);
        var tokens = cookieDecoded.split(";");

        for (c of tokens) {
            var tmp = c.split("=");
            var key = tmp[0];
            var value = tmp[1];

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
        var list = this.map["menus"];
        var size = list.length;
        var lastIdx = size - 1;

        var str = "[";

        for (m of list) {
            str += JSON.stringify(m);
            if (m !== list[lastIdx]) str += ",";
        }

        str = "]";
        var encoded = encodeURIComponent(str);
        document.cookie = `menus=${encoded}; path=/;`;

    },

    remove: function (name) {

    },

    add: function (name, value) {

    },

    addItem: function (name, item) {
        var list = this.map[name];
        list.push(item);
    },

    set: function (name, value) {
        document.cookie = name + "=" + value + "; path=/";
    }

}

window.addEventListener("load", function () {

    var sortSection = document.getElementById("sort");
    var prdList = document.getElementById("prd-list");

    var colBtn = sortSection.querySelector(".icon\\:squares_four"); //모바일 버전 세로 정렬 버튼
    var rowBtn = sortSection.querySelector(".icon\\:list_bullets"); //모바일 버전 가로 정렬 버튼
    var colSection = prdList.querySelector(".menu-card-col"); //세로형 카드 섹션
    var rowSection = prdList.querySelector(".menu-card-row"); //가로형 카드 섹션
    var pcSection  = document.querySelector(".prd-list-pc"); //PC 카드 섹션
    
    let alignNumberBox = sortSection.querySelector(".align-number");        // 데이터 표시 갯수 체크

    // URLSearchParams를 사용하여 현재 URL의 파라미터 가져오기
    var params = new URLSearchParams(window.location.search);
    // 현재 페이지의 도메인, 포트를 가져오기. (application.yaml에서 port변경하는 경우를 위함)
    let baseUrl = window.location.origin;

    // 특정 파라미터 값 가져오기
    var c = params.get('c');


    var priceBtn = sortSection.querySelector(".price"); //가격순
    var recommendBtn = sortSection.querySelector(".recommend"); //추천순

    var content = prdList.getElementsByClassName("content");

    //장바구니 아이콘 관련 selector
    var cartSection =  document.querySelector("main");

    // 이전에 선택한 버튼 상태를 쿠키에서 불러옴
    var cookie = new Cookie();
    var previousBtn = cookie.get("previousBtn");

    if (previousBtn === "colBtn") {
        console.log("콜롬 버튼 저장됨");
        col();
    } else if (previousBtn === "rowBtn") {
        row();
    }

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

        var sortType = 1;
        var url = `${baseUrl}/user/api/product?p=1&s=${sortType}&c=${c}`;

        request(url, function (list) {
            bind(list);
            console.log("가격순 정렬 리로드");
        });
    }

    recommendBtn.onclick = function (e) {
        e.preventDefault();

        priceBtn.classList.remove("color:main-6");
        recommendBtn.classList.add("color:main-6");
        var sortType = 2;
        var url = `${baseUrl}/user/api/product?p=1&s=${sortType}&c=${c}`;

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

        url = `${baseUrl}/user/api/product?p=1&c=${c}&r=${alignNumber}`;
        console.log(url, alignNumber);

        // request(url, function (list) {
        //     bind(list);
        //     console.log(`${alignNumber}개씩 출력`);
        // });
    }  
    

    cartSection.onclick = function (e){
        // e.preventDefault();

        // if(!e.target.classList.contains("icon:shopping_cart_simple"))
        //     return;

        if(!e.target.closest(".icon\\:shopping_cart_simple"))
            return;

        console.log("박경인 나왔냐???????????????");

        /*장바구니 아이콘에 div영역 눌러도 안되요 ㅅㄱ*/

    }

    // 가격,추천순 정렬 작업 중 ...
    function request(url, callback, method) {

        method = method || "GET";

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.onload = function () {
            var list = JSON.parse(this.responseText);
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
        for (var m of list) {

            var rowSectiondHTML = `
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
        for (var m of list) {

            var colSectionHTML = `
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
        for (var m of list) {

            var pcSectiondHTML = `
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

