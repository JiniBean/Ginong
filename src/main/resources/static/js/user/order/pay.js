
function formatNumber(num) {
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

window.addEventListener("load", async function () {
    let couponSection = document.querySelector(".n-dropdown");              //드롭다운 전체 영역
    let couponBtn = couponSection.querySelector("#dropdown-btn");           //드롭다운 버튼
    let couponList = couponSection.querySelector("#dropdown-list");         //드롭다운 리스트

    const pointSection = document.querySelector("#point-section");          //적립금 영역
    const pointInput = pointSection.querySelector("input[name='point']");   //적립금 입력 칸
    const remainPoint = pointSection.querySelector(".remain-point");        //사용자 보유 적립금
    const usePointBtn = pointSection.querySelector(".use-point-btn");       //전액 사용 버튼
    const minCheck = pointSection.querySelector(".min-check");              //최소 금액 유효성 검사 문구
    const maxCheck = pointSection.querySelector(".max-check");              //최대 금액 유효성 검사 문구

    const totalSpan = document.querySelector("#total-price");                //총 상품금액 dataSet 영역
    const paySummary = document.querySelector("#payment-summary");           //결제정보 섹션
    const couponAmt = paySummary.querySelector(".coupon");                    //쿠폰 적용 금액 영역
    const pointAmt = paySummary.querySelector(".point");                      //포인트 적용 금액 영역
    const dlvryAmt = paySummary.querySelector(".dlvry")                       //배송비 영역
    const totalAmt = paySummary.querySelector(".total");
    const totalInput = paySummary.querySelector(".total-input");              //총 결제 금액 영역

    const refundPoint = document.querySelector("input[id='refund-point']");
    const refundPayment = document.querySelector("input[id='refund-payment']");
    let totalProductPrice = parseInt(totalSpan.dataset.total);

    let orderId = totalSpan.dataset.oid;
    let couponDisc; //쿠폰 할인 금액
    let pointDisc;  //포인트 할인 금액

    let member = await getMember();

    let payment = {
        type: 1,
        totalAmt: 0,
        refundType: true,
        categoryId: 0,
        deliveryFeeCategoryId: 0,
        orderId: orderId
    }
    let couponHistory = {
        id: 0,
        couponId: 0,
        usedAmt: 0,
        orderId: orderId
    }
    let pointHistory = {plma: -1, amount: 0}

    //적립금으로 환불하기 눌렀을 때
    refundPoint.onclick = function () {
        payment.refundType = true;
    }

    //적립금으로 환불하기 눌렀을 때
    refundPayment.onclick = function () {
        payment.refundType = false;
    }

    //드롭다운 버튼 선택 했을 때
    couponBtn.onclick = function (e) {
        //쿠폰 리스트 보였다 안보였다
        couponList.classList.toggle("active");
    }

    //드롭다운 리스트에서 옵션 하나 선택 했을 때
    couponList.onclick = function (e) {

        //선택한 쿠폰 보여주기
        couponBtn.textContent = e.target.textContent;
        couponList.classList.remove("active");

        //쿠폰의 데이터로 할인 가격 계산하기
        let amt = parseInt(e.target.dataset.amut);
        if (e.target.dataset.unit === '%')
            couponDisc = totalProductPrice * (amt / 100);
        else
            couponDisc = amt;

        //쿠폰 가격 뿌려주기
        couponAmt.textContent = formatNumber(couponDisc);
        total(couponDisc, pointDisc);

        //사용한 쿠폰 정보 input에 뿌려주기
        couponHistory.id = e.target.dataset.id;
        couponHistory.couponId = e.target.dataset.cid;
        couponHistory.usedAmt = couponDisc;
    }

    pointInput.oninput = function (e) {

        // 사용자가 입력한 포인트와 잔여 포인트 비교
        let inputValue = parseInt(e.target.value);
        let point = parseInt(remainPoint.dataset.point);
        let check = validate(inputValue, point);
        if (check) {
            //인풋 영역에 금액 뿌려주기
            let remain = point - inputValue;
            remainPoint.textContent = formatNumber(remain) + ' P';
            pointInput.textContent = String(inputValue);
            pointInput.value = inputValue;

            //결제정보 영역에 적립금 뿌려주기
            pointDisc = inputValue;
            pointAmt.textContent = formatNumber(pointDisc);
            total(couponDisc, pointDisc);
            pointHistory.amount = pointDisc;
        }

    }

    usePointBtn.onclick = function (e) {

        let point = parseInt(remainPoint.dataset.point);
        let inputValue = point;

        let check = validate(inputValue, point);
        if (check) {
            //인풋 영역에 금액 뿌려주기
            remainPoint.textContent = "0 P";
            pointInput.value = point;

            //결제정보 영역에 적립금 뿌려주기
            pointDisc = inputValue;
            pointAmt.textContent = formatNumber(pointDisc);
            total(couponDisc, pointDisc);
            pointHistory.amount = pointDisc;
        }
    };

    // 적립금 1000원 이상, 잔여 적립금 이상 못 쓰게 Validation Check
    function validate(inputValue, point) {

        //1,000원 이상, 보유 적립금 이하
        if (1000 <= inputValue && inputValue <= point) {
            minCheck.classList.add("d:none");
            maxCheck.classList.add("d:none");
            return true;
        }

        //1,000원 이하
        if (inputValue < 1000) {
            minCheck.classList.remove("d:none");
            maxCheck.classList.add("d:none");
            return false;
        }

        //보유 적립금 이상
        if (inputValue > point) {
            minCheck.classList.add("d:none");
            maxCheck.classList.remove("d:none");
            return false;
        }
        return false;
    }

    //총 결제금액 꽂아주기
    function total(couponDisc = 0, pointDisc = 0) {

        let dlvry = parseInt(dlvryAmt.dataset.cost);
        let cost = parseInt(totalSpan.dataset.total) - (couponDisc + pointDisc) + dlvry;
        totalAmt.textContent = formatNumber(cost);
        payment.totalAmt = cost;

    }

    const button = document.getElementById("payment-button");

    // ------  결제위젯 초기화 ------
    const clientKey = "test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm";
    const customerKey = member.id;
    const paymentWidget = PaymentWidget(clientKey, customerKey); // 회원 결제

// ------  결제 UI 렌더링 ------
    paymentMethodWidget = paymentWidget.renderPaymentMethods(
        "#payment-method",
        {value: totalInput.value},
        {variantKey: "DEFAULT"}
    );
// ------  이용약관 UI 렌더링 ------
    paymentWidget.renderAgreement("#agreement", {variantKey: "AGREEMENT"});


// ------ '결제하기' 버튼 누르면 결제창 띄우기 ------
    button.addEventListener("click", function (e) {
        e.preventDefault();
        // request 정보
        let orderName = totalSpan.dataset.oname
        let orderSize = totalSpan.dataset.size
        if (parseInt(orderSize) > 0)
            orderName += ` 외 ${orderSize}건`

        payment.deliveryFeeCategoryId = totalProductPrice > 30000 ? 2 : 1

        console.log(payment.toString());
        console.log(pointHistory.toString());
        console.log(couponHistory.toString());
        // paymentWidget.requestPayment({
        //     orderId: orderId,
        //     orderName: orderName,
        //     successUrl: window.location.origin + "/order/complete",
        //     failUrl: window.location.origin + "/order/fail",
        //     customerEmail:member.email,
        //     customerName: member.name,
        //     customerMobilePhone: member.phone,
        // });
    });


    async function getMember() {
        let baseUrl = window.location.origin;
        let url = `${baseUrl}/api/member/userinfo`;
        let response = await fetch(url);
        return await response.json();
    }


});
