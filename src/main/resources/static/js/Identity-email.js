document.addEventListener('DOMContentLoaded', function() {
    // 인증 확인 버튼 클릭 이벤트 리스너 추가
    document.getElementById('verify_btn').addEventListener('click', function(event) {
        event.preventDefault(); // 기본 동작 중단

        // 입력된 인증번호 가져오기
        let verificationCode = document.querySelector('.verification-code').value;

        // 서버로 인증번호 전송
        let xhr = new XMLHttpRequest();
        xhr.open('GET', '/mailCheck?userNumber=' + verificationCode, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    let response = JSON.parse(xhr.responseText);
                    if (response.success) {
                        // 인증이 확인되었을 때
                        let verificationResult = document.querySelector('.verification-result');
                        verificationResult.textContent = '인증이 확인되었습니다.';
                        verificationResult.style.color = 'green';
                        sessionStorage.setItem("verification-email","Y");
                    } else {
                        // 인증번호가 일치하지 않을 때
                        let verificationResult = document.querySelector('.verification-result');
                        verificationResult.textContent = '인증번호를 다시 한번 확인해주세요.';
                        verificationResult.style.color = 'red';
                    }
                } else {
                    alert('서버 오류가 발생했습니다.');
                }
            }
        };
        xhr.send();
    });

    // 이메일 발송 버튼 클릭 이벤트 리스너 추가
    document.getElementById('send-mail-btn').addEventListener('click', function(event) {
        event.preventDefault(); // 기본 동작 중단

        // 이메일 주소 가져오기
        let email = document.querySelector('input[name="mail"]').value;

        // 서버로 이메일 주소 전송
        let xhr = new XMLHttpRequest();
        xhr.open('POST', '/mailSend', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    let response = JSON.parse(xhr.responseText);
                    if (response.success) {
                        alert('이메일을 성공적으로 보냈습니다.');
                    } else {
                        alert('이메일 전송에 실패했습니다.');
                    }
                } else {
                    alert('서버 오류가 발생했습니다.');
                }
            }
        };
        xhr.send('mail=' + encodeURIComponent(email));
    });
});
