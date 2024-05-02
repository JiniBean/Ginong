//============================================  모바일  ============================================

//세션에 임시저장한 값 가져오기
window.addEventListener("load", function(e){

    // sec1
    let sec1 = document.querySelector("#sec1");

    let name = sec1.querySelector(".name");
    let email = sec1.querySelector(".email");
    let verifyNum = sec1.querySelector(".verify-num");
    let phone = sec1.querySelector(".phone");

    //쿠키에서 꺼내서 담아줌
    const cookie = new Cookie();
    const map = cookie.get("userInfo");
    console.log("cookie에서 꺼낸" , map);


    //전화번호 입력 시 11자리만 입력되도록
    {
        phone.oninput = function (){
            // 숫자만 입력 가능하게 만들기
            this.value = this.value.replace(/[^0-9]/g, "");
            // 11자리 이상 입력하면 11자리까지 잘라내기
            this.value = this.value.length <= 11 ? this.value : this.value.slice(0, 11);
        }
    }


});


//이전다음 버튼
window.addEventListener("load", function(e){

    // sec1의 버튼
    let sec1 = document.querySelector("#sec1");

    //sec1 이전 다음 버튼들
    let btnBox = sec1.querySelector(".btn-box");
    let prevBtn = btnBox.querySelector(".prev");
    let nextBtn = btnBox.querySelector(".next");

    //유효성 값 검사
    let name = sec1.querySelector(".name");
    let verifyName = sec1.querySelector(".verify-name");
    let phone = sec1.querySelector(".phone");
    let verifyPhone = sec1.querySelector(".verify-phone");

    //이전 버튼
    prevBtn.onclick = function (e){

        e.preventDefault();

        //쿠키에 입력했던 내용 저장
        {
            let sec1 = document.querySelector("#sec1");

            let name = sec1.querySelector(".name").value;
            let email = sec1.querySelector(".email").value;
            let phone = sec1.querySelector(".phone").value;

            const data = {name, email, phone};

            const cookie = new Cookie();
            cookie.addItem("userInfo",data);
            cookie.save();

        }

        location.href="/user/signup/step1";

    }

    //다음버튼
    nextBtn.onclick = function (e){

        e.preventDefault();

        if (nextBtn.classList.contains('disabled'))
            return;

        //초기화
        verifyName.classList.add("d:none");
        verifyPhone.classList.add("d:none");

        //값이 다 있는 지 유효성 검사

        //이름 유효성 검사
        if(!name.value){
            name.setAttribute('autofocus',true);
            verifyName.classList.remove("d:none");
            return;
        }

        //전화번호 유효성 검사
        if(!phone.value){
            phone.setAttribute('autofocus',true);
            verifyPhone.classList.remove("d:none");
            return;
        }

        save();

        location.href="/user/signup/step3";
    }

    function save(){

        let sec1 = document.querySelector("#sec1");

        let name = sec1.querySelector(".name").value;
        let email = sec1.querySelector(".email").value;
        let phone = sec1.querySelector(".phone").value;
        //let verifyNum = sec1.querySelector(".verify-num").value;
        //let agree = sec1.querySelector(".agree").checked.toString();

        let data = {name: name, email : email, phone : phone};

        //세션에 이름 저장
        sessionStorage.setItem("name",name);

        //쿠키에 데이터 저장
        let cookie = new Cookie();
        cookie.get("userInfo");
        cookie.addItem("userInfo", data);
        cookie.save();

    }

});

Cookie.prototype = {
    get : function(name){
        return this.map[name];  // 쿠키객체마다 공유해야하기 때문에 this 작성이 필수
    },
    save : function() {

        let list = this.map["userInfo"];
        let size = list.length;
        let lastIndex = size-1;

        str ="[";

        for(let m of this.map["userInfo"]){
            str+=JSON.stringify(m);
            if(m!==list[lastIndex])
                str+=",";
        }

        str +="]";

        let encoded = encodeURIComponent(str);
        document.cookie = `userInfo=${encoded}; path=/user/signup`;

    },addItem : function(name, item) {
        console.log(this.map[name]);
        let list = this.map[name];
        list.push(item);
    }
}

function Cookie(){

    this.map = {};

    let cookieDecoded = decodeURIComponent(document.cookie);
    let cookieTokens = cookieDecoded.split(";");

    console.log(cookieTokens);

    for(const c of cookieTokens){
        const temp = c.split("=");
        const key = temp[0];
        const value = JSON.parse(temp[1]);

        this.map[key] = value;
    }

}