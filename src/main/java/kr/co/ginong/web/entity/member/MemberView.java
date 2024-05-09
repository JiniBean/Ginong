package kr.co.ginong.web.entity.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date    joinDate;
    private Date    deleteDate;
    private boolean state;
    private boolean agree;

    private String   addr1;
    private String   addr2;

    private int cost;


}
