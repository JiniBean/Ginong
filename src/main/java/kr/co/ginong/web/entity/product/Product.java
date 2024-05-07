package kr.co.ginong.web.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long    id;
    private String  name;
    private int     price;
    private int     weight;
    private String  thumbnailName;
    private String  thumbnailPath;
    private String  exp;
    private String  desc;
    private boolean state;
    private int     quantity;

    private Long    categoryId;
    private Long    storageTypeId;
    private Long    memberId;
    private Long    quantityCategoryId;
    private Long    weightCategoryId;
}
