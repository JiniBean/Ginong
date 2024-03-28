function Cookie() {
    this.map = {};

    if(document.cookie){
        var cookieDecoded = decodeURIComponent(document.cookie);
        var tokens = cookieDecoded.split(";");

        for (c of tokens){
            var tmp = c.split("=");
            var key = tmp[0];
            var value = tmp[1];

            this.map[key] = value;
        }
    }

}

Cookie.prototype = {
    get: function (name){
        return this.map[name];
    },

    save: function () {
        // document.cookie = "menus=hh; path=/;";
        var list = this.map["menus"];
        var size = list.length;
        var lastIdx = size -1;

        var str = "[";

        for(m of list){
            str += JSON.stringify(m);
            if(m !== list[lastIdx])
                str += ",";
        }

        str = "]";
        var encoded = encodeURIComponent(str);
        document.cookie = `menus=${encoded}; path=/;`;

    },

    remove : function (name){

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
window.addEventListener("load", function (){

    var sortSection = document.getElementById("sort");
    var prdList = document.getElementById("prd-list");

    var colBtn= sortSection.querySelector(".icon\\:squares_four"); //모바일 버전 세로 정렬 버튼
    var rowBtn= sortSection.querySelector(".icon\\:list_bullets"); //모바일 버전 가로 정렬 버튼
    var colSection = prdList.querySelector(".menu-card-col"); //세로형 카드 섹션
    var rowSection = prdList.querySelector(".menu-card-row"); //가로형 카드 섹션


    var priceBtn = sortSection.querySelector(".price"); //가격순
    var recommendBtn = sortSection.querySelector(".recommend"); //추천순

    var content = prdList.getElementsByClassName("content");


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
        cookie.set("previousBtn","colBtn");
        e.preventDefault();
    }

    // 가로형 상품 카드로 바꾸기
    rowBtn.onclick = function (e){
        row();
        cookie.set("previousBtn","rowBtn");
        e.preventDefault();
    }

    function col() {
        rowBtn.classList.remove("d:none");
        colSection.classList.remove("d:none");

        colBtn.classList.add("d:none");
        rowSection.classList.add("d:none");
    }

    function row(){
        colBtn.classList.remove("d:none");
        rowSection.classList.remove("d:none");

        rowBtn.classList.add("d:none");
        colSection.classList.add("d:none");
    }

});

