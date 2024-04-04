function formatNumber(num) {
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


window.addEventListener("load", function () {
    var couponSection = document.querySelector(".n-dropdown");      //드롭다운 전체 영역
    var couponBtn = couponSection.querySelector("#dropdown-btn");   //드롭다운 버튼
    var couponList = couponSection.querySelector("#dropdown-list"); //드롭다운 리스트
    var couponInput = couponSection.querySelector(".coupon-input"); //드롭다운 선택 값 input(hidden)

    var totalSpan = document.querySelector("#total-price");         //총 상품금액 dataSet 영역
    var paySummary = document.querySelector("#payment-summary");    //결제정보 섹션
    var couponAmt = paySummary.querySelector(".coupon");            //쿠폰 적용 금액 영역
    var pointAmt = paySummary.querySelector(".point");              //포인트 적용 금액 영역
    var dlvryAmt = paySummary.querySelector(".dlvry")               //배송비 영역
    var totalAmt = paySummary.querySelector(".total");              //총 결제 금액 영역

    var couponDisc; //쿠폰 할인 금액
    var pointDisc;  //포인트 할인 금액

    //드롭다운 버튼 선택 했을 때
    couponBtn.onclick = function (e) {
        //쿠폰 리스트 보였다 안보였다
        var active = couponList.classList.contains("active");
        if (active)
            couponList.classList.remove("active");
        else
            couponList.classList.add("active");
    }

    //드롭다운 리스트에서 옵션 하나 선택 했을 때
    couponList.onclick = function (e) {

        //선택한 쿠폰 보여주기
        couponBtn.textContent = e.target.textContent;
        couponInput.value = e.target.dataset.id
        couponList.classList.remove("active");

        //쿠폰의 데이터로 할인 가격 계산하기
        var amut = e.target.dataset.amut;
        if (e.target.dataset.unit === '%')
            couponDisc = totalSpan.dataset.total * (amut / 100);
        else
            couponDisc = amut;

        //쿠폰 가격 뿌려주기
        couponAmt.textContent = formatNumber(couponDisc);
        total(couponDisc, 0);

    }


    //총 결제금액 꽂아주기
    function total(couponDisc, pointDisc) {
        couponDisc = couponDisc || 0;
        pointDisc = pointDisc || 0;
        var dlvry = dlvryAmt.dataset.cost;
        var cost = totalSpan.dataset.total - couponDisc - pointDisc - dlvry;

        totalAmt.textContent = formatNumber(cost);
    }

});

window.addEventListener("load", function () {
    const pointSection = document.querySelector("#point-section");
    const pointInput = pointSection.querySelector(".point-input");
    const remainPoint = pointSection.querySelector(".remain-point");

    const usePointBtn = pointSection.querySelector(".use-point-btn");

    const minCheck = pointSection.querySelector(".min-check");
    const maxCheck = pointSection.querySelector(".max-check");

    let point = remainPoint.dataset.point;

    pointInput.oninput = function (e) {

        // 사용자 잔여 포인트

        // 사용자가 입력한 포인트
        var inputValue = parseInt(e.target.value);
        console.log("point = ", point);
        console.log("userInputVal = ", inputValue)


        // 포인트 1000원 이상, 잔여 포인트 이상 못 쓰게 Validation Check
        if (inputValue < 1000){
            minCheck.classList.remove("d:none");
            maxCheck.classList.add("d:none");
        }

        if (inputValue > point){
            minCheck.classList.add("d:none");
            maxCheck.classList.remove("d:none");
        }

        if(1000 < inputValue && inputValue <= point){
            minCheck.classList.add("d:none");
            maxCheck.classList.add("d:none");
        }

    }

    usePointBtn.onclick = function (e) {
        if(point < 1000){
            minCheck.classList.remove("d:none");
            maxCheck.classList.add("d:none");
            return;
        }

        remainPoint.textContent = "0 P";
        pointInput.value = point;


    };


});