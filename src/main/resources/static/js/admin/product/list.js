window.addEventListener("load", function () {

    let checkAll = document.querySelector(".all"); //전체 선택 체크 박스

    //전체 선택 눌렀을 때
    checkAll.onclick = function () {
      let checks= document.querySelectorAll(".n-toggle-type\\:check");

      // 전체 선택 버튼의 상태 값에 따라 모든 체크 박스 체크 상태 바꾸기
      checks.forEach(check =>{
          if (checkAll.checked)
              check.checked = true;
          else
              check.checked = false;
      })

    }
})