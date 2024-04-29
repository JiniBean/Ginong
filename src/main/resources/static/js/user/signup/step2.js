//============================================  모바일  ============================================

//세션에 임시저장한 값 가져오기
window.addEventListener("load", function(e){

    // sec1
    let sec1 = document.querySelector("#sec1");

    let name = sec1.querySelector(".name");
    let email = sec1.querySelector(".email");
    let verifyNum = sec1.querySelector(".verify-num");
    let phone = sec1.querySelector(".phone");
  //  let agree = sec1.querySelector(".agree");

    //세션에서 담아줌
    {
        const step2DataString = sessionStorage.getItem('step2Data');
        const userData = JSON.parse(step2DataString);

        if(userData){
            name.value=`${userData.name}`;
            email.value = `${userData.email}`;
            verifyNum.value=`${userData.verifyNum}`;
            phone.value = `${userData.phone}`;
           // agree.checked = `${userData.agree}`==='true' ? true : false;
        }

    }

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
        save();

        location.href="/user/signup/step1";

    }

    //다음버튼
    nextBtn.onclick = function (e){

        e.preventDefault();

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

        //이메일 유효성 검사
        let verificationEmail = sessionStorage.getItem('verification-email');
        let verifyEmail = JSON.parse(verificationEmail);

        if(!verifyEmail){
            let verificationResult = document.querySelector('.verification-result');
            verificationResult.textContent='인증을 완료해주세요';
            verificationResult.style.color = 'red';
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
        let verifyNum = sec1.querySelector(".verify-num").value;
        //let agree = sec1.querySelector(".agree").checked.toString();

        const data = { name, email,verifyNum, phone }

        //세션에 데이터 임시저장
        sessionStorage.setItem("step2Data",JSON.stringify(data));
    }

});