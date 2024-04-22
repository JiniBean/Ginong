//============================================  모바일  ============================================

//세션에 임시저장한 값 가져오기
window.addEventListener("load", function(e){

    // sec1
    let sec1 = document.querySelector("#sec1");

    //세션에서 값 꺼내기
    const step2DataString = sessionStorage.getItem('step2Data');
    const userData = JSON.parse(step2DataString);

    let name = sec1.querySelector(".name");
    let email = sec1.querySelector(".email");
    let verifyNum = sec1.querySelector(".verify-num");
    let phone = sec1.querySelector(".phone");
    let agree = sec1.querySelector(".agree");

    if(userData){
        name.value=`${userData.name}`;
        email.value = `${userData.email}`;
        verifyNum.value=`${userData.verifyNum}`;
        phone.value = `${userData.phone}`;
        agree.checked = `${userData.agree}`==='true' ? true : false;
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

    prevBtn.onclick = function (e){

        e.preventDefault();
        save();

        location.href="/user/signup/step1";

    }

    nextBtn.onclick = function (e){

        e.preventDefault();

        //값이 다 있는 지 유효성 검사
        // alert("이름을 입력해주세요");

        // alert("전화번호를 입력해주세요");
        // alert("이메일 인증을 완료해주세요");


        save();

        location.href="/user/signup/step3";
    }

    function save(){

        let sec1 = document.querySelector("#sec1");

        let name = sec1.querySelector(".name").value;
        let email = sec1.querySelector(".email").value;
        let phone = sec1.querySelector(".phone").value;
        let verifyNum = sec1.querySelector(".verify-num").value;
        let agree = sec1.querySelector(".agree").checked.toString();

        const data = { name, email,verifyNum, phone, agree }

        //세션에 데이터 임시저장
        sessionStorage.setItem("step2Data",JSON.stringify(data));
    }

});



//============================================  pc  ============================================

