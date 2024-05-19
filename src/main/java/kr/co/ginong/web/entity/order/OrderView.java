package kr.co.ginong.web.entity.order;

import java.util.Date;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderView {
    private Long	id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    date;
    private Long    categoryId;
    private String  category;
    private Long	itemId;
    private Integer count;
    private Long	productId;
    private String  prettyName;
    private String  imgName;
    private String  imgPath;
    private Integer price;
    private Integer quantity;

    private Long    memberId;
    private Long    cancelId;
    private Date    cnclDate;

    private Long    exchangeId;
    private String  exchDesc;
    private Integer exchState;
    private Integer exchQty;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    exchRegDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    exchApvlDate;
    private Long    exchCtgryId;
    private String  exchCtgryName;
    private Boolean exchDlvyState;

    private Long    refundID;
    private String  rfndDesc;
    private Integer rfndState;
    private Integer rfndQty;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    rfndRegDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    rfndApvlDate;
    private Long    rfndCtgryId;
    private String  rfndCtgryName;
    private Boolean rfndDlvyState;

    private Integer totalAmt;
    private String  receiverName;

}
