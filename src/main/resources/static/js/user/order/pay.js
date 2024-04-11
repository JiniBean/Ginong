function formatNumber(num) {
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

window.addEventListener("load", function () {
    let couponSection = document.querySelector(".n-dropdown");              //드롭다운 전체 영역
    let couponBtn = couponSection.querySelector("#dropdown-btn");           //드롭다운 버튼
    let couponList = couponSection.querySelector("#dropdown-list");         //드롭다운 리스트
    let couponInput = couponSection.querySelector(".coupon-input");         //드롭다운 선택 값 input(hidden)

    const pointSection = document.querySelector("#point-section");          //적립금 영역
    const pointInput = pointSection.querySelector(".point-input");          //적립금 입력 칸
    const remainPoint = pointSection.querySelector(".remain-point");        //사용자 보유 적립금
    const usePointBtn = pointSection.querySelector(".use-point-btn");       //전액 사용 버튼
    const minCheck = pointSection.querySelector(".min-check");              //최소 금액 유효성 검사 문구
    const maxCheck = pointSection.querySelector(".max-check");              //최대 금액 유효성 검사 문구

    let totalSpan = document.querySelector("#total-price");                 //총 상품금액 dataSet 영역
    let paySummary = document.querySelector("#payment-summary");            //결제정보 섹션
    let couponAmt = paySummary.querySelector(".coupon");                    //쿠폰 적용 금액 영역
    let pointAmt = paySummary.querySelector(".point");                      //포인트 적용 금액 영역
    let dlvryAmt = paySummary.querySelector(".dlvry")                       //배송비 영역
    let totalAmt = paySummary.querySelector(".total");                      //총 결제 금액 영역

    let couponDisc; //쿠폰 할인 금액
    let pointDisc;  //포인트 할인 금액


    //드롭다운 버튼 선택 했을 때
    couponBtn.onclick = function (e) {
        //쿠폰 리스트 보였다 안보였다
        let active = couponList.classList.contains("active");
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
        let amut = parseInt(e.target.dataset.amut);
        if (e.target.dataset.unit === '%')
            couponDisc = parseInt(totalSpan.dataset.total) * (amut / 100);
        else
            couponDisc = amut;

        //쿠폰 가격 뿌려주기
        couponAmt.textContent = formatNumber(couponDisc);
        total(couponDisc, pointDisc);

    }

    pointInput.oninput = function (e) {

        // 사용자가 입력한 포인트
        let inputValue = parseInt(e.target.value);
        let point = parseInt(remainPoint.dataset.point);
        let check = validate(inputValue, point);
        if(check){
            //인풋 영역에 금액 뿌려주기
            let remain = point-inputValue;
            remainPoint.textContent = formatNumber(remain)+' P';
            pointInput.value =  inputValue;

            //결제정보 영역에 적립금 뿌려주기
            pointDisc = inputValue;
            pointAmt.textContent = formatNumber(pointDisc);
            total(couponDisc,pointDisc);
        }

    }

    usePointBtn.onclick = function (e) {

        let point = parseInt(remainPoint.dataset.point);
        let inputValue = point;

        let check = validate(inputValue, point);
        if(check){
            //인풋 영역에 금액 뿌려주기
            remainPoint.textContent = "0 P";
            pointInput.value = point;

            //결제정보 영역에 적립금 뿌려주기
            pointDisc = inputValue;
            pointAmt.textContent = formatNumber(pointDisc);
            total(couponDisc,pointDisc);
        }
    };

    // 적립금 1000원 이상, 잔여 적립금 이상 못 쓰게 Validation Check
    function validate(inputValue, point){

        //1,000원 이상, 보유 적립금 이하
        if(1000 <= inputValue && inputValue <= point){
            minCheck.classList.add("d:none");
            maxCheck.classList.add("d:none");
            return true;
        }

        //1,000원 이하
        if (inputValue < 1000){
            minCheck.classList.remove("d:none");
            maxCheck.classList.add("d:none");
            return false;
        }

        //보유 적립금 이상
        if (inputValue > point){
            minCheck.classList.add("d:none");
            maxCheck.classList.remove("d:none");
            return false;
        }
        return false;
    }

    //총 결제금액 꽂아주기
    function total(couponDisc= 0, pointDisc= 0) {

        let dlvry = parseInt(dlvryAmt.dataset.cost);
        let cost = parseInt(totalSpan.dataset.total) - (couponDisc + pointDisc) + dlvry;
        totalAmt.textContent = formatNumber(cost);
        let totalInput = paySummary.querySelector(".total-input");
        totalInput.value = cost;

    }

});
