window.addEventListener("load", function () {

    async function confirm() {
        var requestData = {
            paymentKey: urlParams.get("paymentKey"),
            orderId: urlParams.get("orderId"),
            amount: urlParams.get("amount"),
        };

        const response = await fetch("/confirm", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestData),
        });

        const json = await response.json();

        if (!response.ok) {
            // TODO: 결제 실패 비즈니스 로직을 구현하세요.
            console.log(json);
            window.location.href = `/fail?message=${json.message}&code=${json.code}`;
        }

        // TODO: 결제 성공 비즈니스 로직을 구현하세요.
        console.log(json);
    }
    confirm();
})