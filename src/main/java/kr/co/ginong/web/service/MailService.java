package kr.co.ginong.web.service;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Resource
    private JavaMailSender javaMailSender; // JavaMailSender를 주입받는다.

    static String senderEmail = "tlstpstpdl11@naver.com"; // 발신자 이메일 주소
    static int number; // 생성된 인증 번호를 저장하는 변수

    // 인증 번호 생성 메서드
    public static void createNumber() {
        number = (int) (Math.random() * (90000)) + 100000; // 100000에서 999999 사이의 랜덤한 숫자 생성
    }

    // 이메일을 생성하는 메서드
    public MimeMessage CreateMail(String mail) {
        createNumber(); // 인증 번호 생성

        MimeMessage message = javaMailSender.createMimeMessage(); // MimeMessage 객체 생성(이메일의 내용, 수신자, 발신자 등을 설정하고 전송할 수 있음)
        try {
            message.setFrom(senderEmail); // 발신자 설정
            message.setRecipients(MimeMessage.RecipientType.TO, mail); // 수신자 설정
            message.setSubject("이메일 인증"); // 이메일 제목 설정
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>"; // 이메일 본문: 인증 번호 안내
            body += "<h1>" + number + "</h1>"; // 생성된 인증 번호 추가
            body += "<h3>" + "감사합니다." + "</h3>"; // 이메일 본문: 감사 메시지

            message.setContent(body, "text/html; charset=utf-8"); // 이메일 본문 설정
        } catch (MessagingException e) {
            e.printStackTrace(); // MessagingException 발생 시 예외 처리
        }

        return message; // 생성된 MimeMessage 반환
    }

    // 이메일을 보내는 메서드
    public int sendMail(String mail) {
        MimeMessage message = CreateMail(mail); // 이메일 생성

        javaMailSender.send(message); // 생성된 이메일 발송

        return number; // 생성된 인증 번호 반환
    }


}
