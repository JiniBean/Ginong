window.addEventListener("load", function(){

    var numberBox = this.document.querySelector(".numberBox");
    var numberInput = numberBox.getElementsByClassName("number-input")[0];

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

    }

});