//============================================  모바일  ============================================

//============================================  sec2  ============================================
//세션에 임시저장한 값 가져오기
window.addEventListener("load", function(e){

    // sec2
    const sec2 = document.querySelector("#sec2");

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
    let verifyName = sec2.querySelector(".verify-kor");

    //1. 아이디 입력 시
    let timeoutId;

    userName.oninput = function (e){

        //유효성검사 내용들 clear
        verifyName.classList.add("d:none");

        // 입력된 값 가져오기
        let userInputData = e.data;

        if(timeoutId!==undefined){
            clearTimeout(timeoutId);
        }

        // 한글이 입력되었다면 입력을 취소하고 함수를 빠져나감
        if(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/.test(userInputData)){
            verifyName.classList.remove("d:none");
            userName.value = "";
            clearTimeout(timeoutId);
        }

        timeoutId = setTimeout(()=>{

            //사용자가 입력한 값
            let userNameValue = userName.value;
            console.log("==========",userNameValue);
            checkUserName(userNameValue);

        },500);

    }

    //DB에서 아이디 있는지 확인
   async function checkUserName(userNameValue){

        //아이디 사용가능
       let  usableDiv= document.getElementsByClassName("check-id")[0];
       //아이디 사용중
       let  disableDiv= document.getElementsByClassName("check-id")[1];

        if(userNameValue!=="") {

            let url=`/api/member/checkUserName`;
                url = `${url}?userName=${userNameValue}`;

            let method = "get";

            let xhr = new XMLHttpRequest();

            xhr.onload = function (){

                if(xhr.status===200){ //아이디 있으니 사용불가
                    disableDiv.classList.remove("d:none");
                    usableDiv.classList.add("d:none");
                    sessionStorage.setItem('nameYn','Y');
                }else { //DB에 아이디 없으니 사용가능
                    usableDiv.classList.remove("d:none");
                    disableDiv.classList.add("d:none");
                    sessionStorage.setItem('nameYn','N');
                }

            };

             xhr.open(method, url);
             xhr.setRequestHeader('Content-Type', 'text/plain');
             xhr.send();

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

    pwdInput.onchange = function (e) {

        if(timeoutId!==undefined){
            clearTimeout(timeoutId);
        }

        timeoutId = setTimeout(()=>{
            let  pwdValue = pwdInput.value;
            let  verifyPwdValue = verifyPwdInput.value;

            if(verifyPwdValue!=="")
                checkVerify(pwdValue,verifyPwdValue);

        },500);

    }

    //비밀번호 값 확인
    function checkVerify(pwdValue,verifyPwdValue){
        let match = document.getElementsByClassName("check-password")[0];
        let mismatch = document.getElementsByClassName("check-password")[1];

        if(pwdValue!==verifyPwdValue){
            match.classList.add("d:none");
            mismatch.classList.remove("d:none");
            sessionStorage.setItem('pwdYn','N');
            return false;
        }

        match.classList.remove("d:none");
        mismatch.classList.add("d:none");
        sessionStorage.setItem('pwdYn','Y');


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

    //유효성검사 값들
    let userName = sec2.querySelector(".user-name");
    let verifyId = sec2.querySelector(".verify-id");
    let pwd = sec2.querySelector(".password");
    let verifyPwd = sec2.querySelector(".verify-pwd");
   // let pwdMatch = sec2.querySelector(".verify-password");

    //이전 버튼 눌렀을 때
    prevBtn.onclick = function (e){
        e.preventDefault();

        save();

        location.href="/signup/step2";

    }

    //회원가입 버튼 눌렀을 때
    signupBtn.onclick = function (e){

        //유효성 검사
        {

            //초기화
            verifyId.classList.add("d:none");
            verifyPwd.classList.add("d:none");

            //검사
            //아이디
            {
                let userName = document.querySelector(".user-name");

                if(!userName.value){
                    verifyId.classList.remove("d:none");
                    return;
                }

                //아이디 사용가능만 가능하도록
                let nameYn = sessionStorage.getItem('nameYn');

                if(nameYn==='Y'){
                    alert("아이디가 사용중입니다.");
                    return;
                }


            }


            //비밀번호
            {
                if(!pwd.value){
                    verifyPwd.classList.remove("d:none");
                    return;
                }

                let regExp = /^(?=.*[a-zA-Z])(?=.*[0-9]).{7,}$/;
                let isValid = regExp.test(pwd.value);

                if(!isValid)
                    return;


                //재확인
                let pwdYn = sessionStorage.getItem('pwdYn');

                if(pwdYn==='N'){
                    let verifyPwd = sec2.querySelector(".verify-password");
                    alert("비밀번호 재확인을 다시 입력해주세요");
                    return;

                }

            }

        }

        save();

        const cookieString = document.cookie;

        const cookies={};
        const cookiePairs = cookieString.split('; ');

        for (const cookiePair of cookiePairs) {
            const [key, value] = cookiePair.split('=');
            cookies[key] = value;
        }

        const cookieName = 'userInfo';
        const encodedValue  = cookies[cookieName];

        let decodedValue;
        let userInfoData;

        if (encodedValue) {
            decodedValue = decodeURIComponent(encodedValue);


            // JSON 객체로 변환
            try {
                userInfoData = JSON.parse(decodedValue);
                console.log("JSON 객체 출력  ",userInfoData); // JSON 객체 출력
            } catch (error) {
                console.error('쿠키 값 파싱 오류:', error);
            }
        } else {
            console.error('쿠키에 userInfo 값이 없습니다.');
        }

        //db에 회원정보 저장
        let url = "/api/member/add";
        let method ="post";

        let xhr = new XMLHttpRequest();

        xhr.withCredentials = true;

        xhr.onload = function(){
           if(xhr.status===200){

               //let url = new URL("/signup/step4",location.origin);

               const name = sessionStorage.getItem("name");

               //url = url + "?name=" +name;

               location.href=`/signup/step4?name=${name}`;

           }else {
              alert("회원가입에 실패했습니다.");
           }
        };

        // 쿠키 값을 JSON 데이터로 변환
        const cookieData = JSON.stringify({
            userInfo: userInfoData
        });

        console.log(cookieData);

        xhr.open(method,url);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(cookieData);


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