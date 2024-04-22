//============================================  모바일  ============================================

//============================================  sec1  ============================================

//이전다음 버튼
window.addEventListener("load", function(e){

    // sec1의 버튼
    let sec1 = document.querySelector("#sec1");
    let sec2 = document.querySelector("#sec2");

    //sec1 이전 다음 버튼들
    let sec1btns = sec1.querySelector(".btns");
    let sec1prevBtn = sec1btns.querySelector(".prev");
    let sec1nextBtn = sec1btns.querySelector(".next");

    //sec2 이전 다음 버튼들
    let sec2btns = sec2.querySelector(".btns");
    let sec2prevBtn = sec2btns.querySelector(".prev");
    let signupBtn = sec2btns.querySelector(".signup");

    //모바일 버전 상단 현단계 알려주는 영역
    let stepDivs = document.querySelector("#stepDivs");

    let step2Div = stepDivs.getElementsByClassName("n-item")[1];
    let step3Div = stepDivs.getElementsByClassName("n-item")[2];

    //원안의 숫자
    let step2Span =step2Div.childNodes[0];
    let step3Span =step3Div.childNodes[0];


    sec1prevBtn.onclick = function (e){
        let url = new URL ("/user/signup/step1", location.origin);
        location.href = url.toString();
    }

    sec1nextBtn.onclick = function (e){

        e.preventDefault();

        step2Div.classList.remove("bg-color:main-6");
        step3Div.classList.add("bg-color:main-6");

        step2Span.classList.remove("color:base-1");
        step3Span.classList.add("color:base-1");

        sec1.classList.add("d:none");
        sec2.classList.remove("d:none");

    }

    sec2prevBtn.onclick = function (e) {
        e.preventDefault();

        step2Div.classList.add("bg-color:main-6");
        step3Div.classList .remove("bg-color:main-6");

        step2Span.classList.add("color:base-1")
        step3Span.classList.remove("color:base-1");

        sec1.classList.remove("d:none");
        sec2.classList.add("d:none");
    }

    signupBtn.onclick = function (e) {
        alert("너무졸리다..........");
    }


});


//============================================  sec2  ============================================


// 아이디 유효성
window.addEventListener("load", function(e){

    // sec2
    let sec2 = document.querySelector("#sec2");

    // 아이디
    let userName = sec2.querySelector(".user-name");

    //1. 아이디 입력 시
    let timeoutId;

    userName.oninput = function (e){

        // 입력된 값 가져오기
        let userInputData = e.data;

        // 한글이 입력되었다면 입력을 취소하고 함수를 빠져나감
        if(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/.test(userInputData)){
            userName.value = userName.value.slice(0, -1);
            return;
        }

        if(timeoutId!==undefined){
            clearTimeout(timeoutId);
        }

        timeoutId = setTimeout(()=>{

            console.log("0.5초후 실행");

            let userNameValue = userName.value;

            console.log("사용자가 입력한 값 : ", userNameValue);

            checkUserName(userNameValue);

        },500);

    }


   async function checkUserName(userNameValue){

       let  usableDiv= document.getElementsByClassName("check-id")[0];
       let  disableDiv= document.getElementsByClassName("check-id")[1];

        if(userNameValue!=="") {

            //db에 값체크 해야함
            let url=`/user/api/member/checkUserName`;

            url = `${url}?userName=${userNameValue}`;

            let resonse = await fetch(url);

            let map = await resonse.json();
            let name = await map.name;

            //사용가능
            if(name===undefined){
                usableDiv.classList.remove("d:none");
                disableDiv.classList.add("d:none");
            }

            //사용중
            if(name!==undefined){
                disableDiv.classList.remove("d:none");
                usableDiv.classList.add("d:none");
            }

        }else {
            usableDiv.classList.add("d:none");
            disableDiv.classList.add("d:none");
        }

    }

});


// 비밀번호 유효성 검사
window.addEventListener("load", function(e){
    // sec2
    let sec2 = document.querySelector("#sec2");
    // 비밀번호 input
    let pwdInput = sec2.querySelector(".password");

    //사용불가
    let disableDiv= document.getElementsByClassName("check-pwd")[0];

    //사용가능
    let usableDiv = document.getElementsByClassName("check-pwd")[1];

    let timeoutId;

    //비밀번호 조건 정규식
    let regExp = /^(?=.*[a-zA-Z])(?=.*[0-9]).{7,}$/;

    pwdInput.oninput = function (e) {

        if(timeoutId!==undefined){
            clearTimeout(timeoutId);
        }

        timeoutId = setTimeout(()=>{
           let  pwdValue = pwdInput.value;

            if(pwdValue!==""){
                let isValid = regExp.test(pwdValue);
                checkPwd(isValid);

            }else {
                console.log("pwdValue3" , pwdValue);
                disableDiv.classList.add("d:none");
                usableDiv.classList.add("d:none");
            }

        },500);

    }

    function checkPwd(isValid){

        if(!isValid){ //사용불가
            console.log("사용불가" , isValid);
            disableDiv.classList.remove("d:none");
            usableDiv.classList.add("d:none");
            return false;
        }

        //사용가능
        console.log("사용가능" , isValid);
        disableDiv.classList.add("d:none");
        usableDiv.classList.remove("d:none");

    }

});


//비밀번호 일치 확인
window.addEventListener("load", function(e){
    // sec2
    let sec2 = document.querySelector("#sec2");
    // 비밀번호 input
    let pwdInput = sec2.querySelector(".password");
    // 비밀번호 확인
    let verifyPwdInput = sec2.querySelector(".verify-password");

    let timeoutId;

    verifyPwdInput.oninput = function (e) {

        if(timeoutId!==undefined){
            clearTimeout(timeoutId);
        }

        timeoutId = setTimeout(()=>{
            let  pwdValue = pwdInput.value;
            let  verifyPwdValue = verifyPwdInput.value;

            checkVerify(pwdValue,verifyPwdValue);

        },500);

    }

    function checkVerify(pwdValue,verifyPwdValue){
        let match = document.getElementsByClassName("check-password")[0];
        let mismatch = document.getElementsByClassName("check-password")[1];

        if(pwdValue!==verifyPwdValue){
            match.classList.add("d:none");
            mismatch.classList.remove("d:none");
            return false;
        }

        match.classList.remove("d:none");
        mismatch.classList.add("d:none");





    }

});


//============================================  pc  ============================================

