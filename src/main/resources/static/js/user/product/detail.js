/*모바일 버전 구매정보 수량증감*/
window.addEventListener("load", function(){

    /*orderInfo section*/
    var orderInfo = this.document.querySelector("#order-info");
    /*증감버튼영역*/
    var numberBox = orderInfo.querySelector(".numberBox");
    var quantityInput = numberBox.querySelector(".quantity-input");

    /*구매총합영역*/
    var total = this.document.querySelector(".total");

    //총 수량
    var totalQuantity = total.querySelector(".total-quantity");

    //상품 가격
    var productPriceValue= this.document.querySelector(".product-price").value;
    var productPrice = parseInt(productPriceValue);

    //총 금액
    var totalPrice = this.document.querySelector(".total-price");
    var totalPriceInput = this.document.querySelector(".total-price-input");

    //popup close
    var close = orderInfo.getElementsByClassName("close")[0];

    //popup open
    var navi = this.document.querySelector(".navi");

    navi.onclick = function(e){
        if(e.target.tagName!='BUTTON')
            return;

        orderInfo.classList.remove("d:none");
    }

    /*증감버튼 계산*/
    numberBox.onclick = function (e) {

        e.preventDefault();

        if(e.target.tagName!='A')
            return;

        //수량 -> int
        var quantity = parseInt(quantityInput.value);

        //클릭한 버튼 구별
        var state = e.target.dataset.btn;

        switch (state) {
            case 'minus' :
                if(quantity==1)
                    return;
                quantityInput.value = quantity-1;
                break;
            case 'plus' :
                if(quantity==10) //최대 재고수량까지(나중에 재고값 가져오기)
                    return;
                quantityInput.value = quantity+1;
                break;
        }

        //총 수량
        totalQuantity.innerText = quantityInput.value;

        // 총금액(수량*상품가격)
        var totalSum = quantityInput.value * productPrice;
        totalPrice.innerText=totalSum.toLocaleString('ko-KR')+"원";
        totalPriceInput.value=totalSum;

    }

    close.onclick = function(){
        orderInfo.classList.add("d:none");
    }

});

/* pc버전 구매정보 수량증감*/
/*
window.addEventListener("load", function(){

    var numberBox = this.document.querySelector(".numberBox");
    var numberInput = numberBox.getElementsByClassName("number-input")[0];
    var totalCount = this.document.querySelector(".total-count");
    var price= this.document.querySelector(".price").value;
    var totalPrice= this.document.getElementsByClassName("total-price")[0];
    var totalPriceInput= this.document.getElementsByClassName("total-price")[1];

    numberBox.onclick = function (e) {

        e.preventDefault();

        if(e.target.tagName!='A')
            return;

        var currentValue = parseInt(numberInput.value);
        var state = e.target.dataset.btn;

        switch (state) {
            case 'minus' :
                if(currentValue==1)
                    return;
                numberInput.value = currentValue-1;
                break;
            case 'plus' :
                if(currentValue==10) //최대 재고수량까지
                    return;
                numberInput.value = currentValue+1;
                break;
        }

        totalCount.innerText = numberInput.value;

        var totalSum = parseInt(numberInput.value) * parseInt(price);
        totalPrice.innerText=totalSum;
        totalPriceInput.value=totalSum;

    }

});*/
