window.addEventListener("load", function () {

    let checkAll = document.querySelector(".all"); //전체 선택 체크 박스

    //전체 선택 눌렀을 때
    checkAll.onclick = function () {
      let checkboxes = document.querySelectorAll("input[type='checkbox']");

      // 전체 선택 버튼의 상태 값에 따라 모든 체크 박스 체크 상태 바꾸기
      checkboxes.forEach(check => check.checked = this.checked)

    }
})
