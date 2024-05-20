package kr.co.ginong.web.entity.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberOrderView {
    private Long memberId;
    private Long orderId;
    @DateTimeFormat(pattern = "yyyyMMdd") // 날짜 형식의 문자열을 해당 패턴에 맞게 파싱하여 자바의 Date 객체로 변환
    private Date orderDate;

    //주문 상태
    private String orderCategoryName;

    //가격 자료형 다른 클래스 확인
    private int totalAmount;

    private String productName;
    private int productPrice;
    private int productQuantity;
}

