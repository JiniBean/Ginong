window.addEventListener("load", function () {
    var totalSpan = document.querySelector("#total-price");
    var couponSection = document.querySelector(".n-dropdown");
    var couponBtn = couponSection.querySelector("#dropdown-btn");
    var couponList = couponSection.querySelector("#dropdown-list");
    var couponInput = couponSection.querySelector(".coupon-input");

    couponBtn.onclick = function (e) {

        var active = couponList.classList.contains("active");
        if(active)
            couponList.classList.remove("active");
        else
            couponList.classList.add("active");
    }

    couponList.onclick = function (e){

        couponBtn.textContent = e.target.textContent;
        couponList.classList.remove("active");
        couponInput.value = e.target.dataset.id

        var amut = e.target.dataset.amut;
        var discount;
        if(e.target.dataset.unit==='%')
            discount = totalSpan.dataset.total*(amut/100);
    }

});