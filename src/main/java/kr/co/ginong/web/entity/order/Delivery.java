package kr.co.ginong.web.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    private long	id;
    private String	zipCode;
    private String	addr1;
    private String	addr2;
    private String	receiverName;
    private String	receiverPhone;
    private String	label;
    private String	note;
    private String	gatePwd;
    private long	categoryId;
    private long	memberId;
    private long	orderId;
    private long	locationId;
    
}
