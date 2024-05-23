package kr.co.ginong.web.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductView {
    private Long    id;
    private String  name;
    private String  prettyName;
    private int     price;
    private int     quantity;
    private int     weight;
    private int     currentAmount;
    private String  thumbnailName;
    private String  thumbnailPath;
    private String  exp;
    private String  desc;
    private boolean state;
    private int     discountRate;
    private int     likeCount;
    private String  categoryName;
    private String  seasonStartDate;
    private String  seasonEndDate;
    private String  descriptionName;

    private Long    categoryId;
    private String  storageType;
    private String  quantityCategory;
    private String  weightCategory;

}
