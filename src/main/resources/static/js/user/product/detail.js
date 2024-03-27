/*모바일 버전 구매정보 수량증감*/
window.addEventListener("load", function(){

    var numberBox = this.document.querySelector(".m-numberBox");
    var numberInput = numberBox.getElementsByClassName("m-number-input")[0];
    var totalCount = this.document.querySelector(".m-total-count");
    var price= this.document.querySelector(".m-price").value;
    var totalPrice= this.document.getElementsByClassName("m-total-price")[0];
    var totalPriceInput= this.document.getElementsByClassName("m-total-price")[1];

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

});

/* pc버전 구매정보 수량증감*/
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

});