//step2
//============================================  모바일  ============================================
// 아이디 유효성검사
window.addEventListener("load", function(e){

    // step3
    let step3 = document.querySelector("#step3");

    // 아이디
    let userName = step3.querySelector(".user-name");

    //한글 입력시 주의사항
    let idInfo = document.querySelector(".id-info");

    //1. 아이디 입력 시
    let timeoutId;

    userName.oninput = function (e){

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
    // step3
    let step3 = document.querySelector("#step3");
    // 비밀번호 input
    let pwdInput = step3.querySelector(".password");

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
    // step3
    let step3 = document.querySelector("#step3");
    // 비밀번호 input
    let pwdInput = step3.querySelector(".password");
    // 비밀번호 확인
    let verifyPwdInput = step3.querySelector(".verify-password");

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

