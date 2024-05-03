//step1

// 전체약관동의 checked
window.addEventListener("load", function(e){

    //선택 div[]
    let checkDivs = this.document.querySelectorAll(".check");
    //전체선택 check 영역 div
    let checkDiv = this.document.querySelector(".check");
    //전체선택 span
    let checkAll = checkDiv.querySelector(".check-all");

    //checkDiv > icon을 클릭했을때
    checkAll.onclick = function (){

        //check 유무
        let isChecked = checkAll.classList.contains('checked');

        //전체 체크 O -> checked remove
        if(isChecked){
            for( let div of checkDivs){
                let iconDiv = div.querySelector(".icon");
                removeChecked(iconDiv);
            }

            return;
        }

        // 전체 체크 X -> checked add
        if(!isChecked)
            for( let div of checkDivs){
                let iconDiv = div.querySelector(".icon");
                addChecked(iconDiv);
            }

    }

});

//체크 클릭 했을 때 활성화/비활성화
window.addEventListener("load", function(e){

    //필수영역 - 필수영역 유효성검사 체크하려고 영역 나누어 놓음
    let chkRequired = this.document.querySelector(".check-required");

    chkRequired.onclick = function (e){

        if(!e.target.classList.contains("icon"))
            return;

        let targetChecked = e.target.classList.contains('checked');

        //선택된 경우
        if(targetChecked){
            removeChecked(e.target);
            return;
        }

        //선택되지 않은 경우
        if(!targetChecked)
            addChecked(e.target);

    }

    //선택영역
    let chkOption = this.document.querySelector(".check-option");

    chkOption.onclick = function (e){

        if(!e.target.classList.contains("icon"))
            return;

        let targetChecked = e.target.classList.contains('checked');

        if(targetChecked){
            removeChecked(e.target);
            return;
        }

        if(!targetChecked)
            addChecked(e.target);

    }

});

//버튼 활성화
function addChecked(element){
    element.classList.replace("icon:checkCircle","icon:check_circle_fill");
    element.classList.replace("icon-color:base-6","icon-color:main-6");
    element.classList.add("checked");
}


//버튼 비활성화
function removeChecked(element){
    element.classList.replace("icon:check_circle_fill","icon:checkCircle");
    element.classList.replace("icon-color:main-6","icon-color:base-6");
    element.classList.remove("checked");
}

//동의버튼 누르면 필수 선택했는지 유효성 검사
window.addEventListener("load", function(e){

    let btn = this.document.querySelector(".n-btn");
    let chkRequired = this.document.querySelector(".check-required");

    btn.onclick = function (e){

        e.preventDefault();



        //필수동의 영역
        {
            let iconList= chkRequired.querySelectorAll(".icon");

            let set = new Set();
            set.add(iconList[0].classList.contains("checked"));
            set.add(iconList[1].classList.contains("checked"));

            if(set.has(false)){
                alert("필수약관에 전부 동의해주세요");
                return;
            }
        }



        //선택동의 영역
        {
            let chkOption = document.querySelector(".check-option");
            let icon= chkOption.querySelector(".icon");

            let isChecked = icon.classList.contains("checked");

            let agree = "N";

            if(isChecked)
                agree="Y";

            const userInfoData = {agree : agree};
            const userInfo = JSON.stringify(userInfoData);

            let str ="[" + userInfo +"]";

            const encodedUserInfo = encodeURIComponent(str);

            document.cookie = `userInfo=${encodedUserInfo}; path=/signup`;

        }

        //get 방식
        // let url = new URL("/signup/step2",location.origin);
        // location.href=url.toString();
        location.href="/signup/step2";

    }

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
            document.cookie = `userInfo=${encoded}; path=/signup`;

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

});


