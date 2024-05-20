// 카테고리 버튼
window.addEventListener("load", function () {
    const category = this.document.querySelector('#category');
    const selectedOptionCategory = category.querySelector('.selected-option-category');
    const optionsCategory = category.querySelectorAll('.option-category');
    const selectedCategory = category.querySelector('.selected-category');
    const optionCategoryList = category.querySelector('.option-category-list');

    //드롭다운 최초 클릭 시 이벤트 처리
    selectedOptionCategory.addEventListener('click', function (e) {
        e.stopPropagation(); //이벤트 버블링 방지
        optionCategoryList.classList.toggle('active'); // 카테고리 옵션 목록의 표시/숨김을 토글
    });

    // 드롭다운 클릭하여 아래로 펼쳐졌을 때 카테고리 옵션 항목 클릭 시 이벤트 처리
    for (const option of optionsCategory) {
        option.addEventListener('click', function (e) {
            e.stopPropagation(); // 이벤트 버블링 방지
            const selectedValueCategory = this.getAttribute('data-value'); // 선택된 카테고리 값
            selectedOptionCategory.textContent = this.textContent; // 선택된 카테고리 표시 업데이트
            selectedCategory.value = selectedValueCategory; // 선택된 카테고리 값 업데이트 (hidden 되어있음)
            optionCategoryList.classList.remove('active'); // 원하는 값 클릭 후 펼쳐진 목록 사라짐
        });
    }

    // 문서의 다른 부분을 클릭했을 때 드롭다운 메뉴가 열려있는 경우 드롭다운 메뉴를 닫음
    window.addEventListener('click', function (e) {
        if (!selectedOptionCategory.contains(e.target)) { // 클릭한 요소가 선택된 카테고리 영역이 아니라면
            optionCategoryList.classList.remove('active'); // 카테고리 옵션 목록을 숨김
        }
    })
});