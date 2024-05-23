package kr.co.ginong.web.entity.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.regex.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberView {
    private long    id;
    private String  name;
    private Date    birthDate;
    private String  userName;
    private String  email;
    private String  pwd;
    private String  phone;
    @DateTimeFormat (pattern = "yyyy-MM-dd") // 날짜 형식의 문자열을 해당 패턴에 맞게 파싱하여 자바의 Date 객체로 변환
    private Date    joinDate;
    private Date    deleteDate;
    private boolean state;
    private boolean agree;

    private String   addr1;
    private String   addr2;
    private String   zipCode;

    private int cost;


}
