package kr.co.ginong.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductView {
    private long    id;
    private String  name;
    private int     price;
    private int     quantity;
    private int     weight;
    private String  thumbnailName;
    private String  thumbnailPath;
    private String  exp;
    private String  desc;
    private boolean state;
    private int     likeCount;

    private String  category;
    private String  storageType;
    private String  admin;
    private String  quantityCategory;
    private String  weightCategory;
}
