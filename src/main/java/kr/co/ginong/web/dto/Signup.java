package kr.co.ginong.web.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Signup {

    private String agree;
    private String name;
    private String email;
    private String phone;
    private String userName;
    private String pwd;
    private String joinRoute;

}
