package kr.co.ginong.web.entity.member;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private long    id;
    private String  name;
    private Date  birthDate;
    private String  userName;
    private String  email;
    private String  pwd;
    private String  phone;
    private Date    joinDate;
    private Date    deleteDate;
    private boolean state;
    private boolean agree;
    private Date birthDate;

}
