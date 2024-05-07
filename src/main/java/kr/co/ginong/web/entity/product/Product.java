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
    private long    id;
    private String  name;
    private int     price;
    private int     weight;
    private String  thumbnailName;
    private String  thumbnailPath;
    private String  exp;
    private String  desc;
    private boolean state;
    private int     quantity;

    private long    categoryId;
    private long    storageTypeId;
    private long    memberId;
    private long    quantityCategoryId;
    private long    weightCategoryId;
}
