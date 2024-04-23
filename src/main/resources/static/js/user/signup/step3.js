//============================================  모바일  ============================================

//============================================  sec2  ============================================
//세션에 임시저장한 값 가져오기
window.addEventListener("load", function(e){

    // sec2
    let sec2 = document.querySelector("#sec2");

    //세션에서 값 꺼내기
    const step3DataString = sessionStorage.getItem('step3Data');
    const userData = JSON.parse(step3DataString);

    let userName = sec2.querySelector(".user-name");
    let pwd = sec2.querySelector(".password");

    //유입경로
    let route = document.getElementsByName("route");

    let joinRoute;

    if(userData){
        joinRoute = `${userData.joinRoute}`;

        switch (joinRoute){
            case 'blog' :
                route[0].checked=true;
                break;
            case 'internet' :
                route[1].checked=true;
                break;
            case 'sns' :
                route[2].checked=true;
                break;
            case 'person' :
                route[3].checked=true;
                break;
        }

        userName.value=`${userData.userName}`;
        pwd.value=`${userData.pwd}`;
    }

});

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
            alert("한글은 입력할 수 없습니다.");
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


    //DB에서 아이디 있는지 확인
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

//이전 버튼 클릭 시 세션에 값 저장
//회원가입 버튼 클릭 후 정보저장
window.addEventListener("load", function(e){
    // sec2
    let sec2 = document.querySelector("#sec2");

    //sec2 이전, 회원가입 버튼들
    let btnBox = sec2.querySelector(".btn-box");
    let prevBtn = btnBox.querySelector(".prev");
    let signupBtn = btnBox.querySelector(".signup");

    prevBtn.onclick = function (e){
        e.preventDefault();

        save();

        location.href="/user/signup/step2";

    }

    signupBtn.onclick = function (e){

        const step3data = save();

        const step2DataString = sessionStorage.getItem('step2Data');
        const step2Data = JSON.parse(step2DataString);

        let data = {...step3data, ...step2Data};

        let url = "/user/api/member/add";
        let method ="post";

        let xhr = new XMLHttpRequest();

        xhr.withCredentials = true;

        xhr.onload = function(){
           if(xhr.status===200){

               let url = new URL("/user/signup/step4",location.origin);

               let name = data.name;

               url = url + "?name=" +name;

               //세션 저장소값 지우고 페이지 넘기기
               sessionStorage.clear();

               location.href=url.toString();

           }else {
              alert("회원가입에 실패했습니다.");
           }
        };

        xhr.open(method,url);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify(data));
    }

    function save(){

        let sec2 = document.querySelector("#sec2");

        let userName = sec2.querySelector(".user-name").value;
        let pwd = sec2.querySelector(".password").value;
        let route = document.getElementsByName("route");

        let joinRoute = "default";

        //유입경로 선택
        for(const radio of route)
            if (radio.checked) {
                joinRoute = radio.value;
                break;
            }

        let data = { userName, pwd, joinRoute }

        //세션에 데이터 임시저장
        sessionStorage.setItem("step3Data",JSON.stringify(data));

        return data;
    }


});