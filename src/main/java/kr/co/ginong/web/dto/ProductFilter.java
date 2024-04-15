package kr.co.ginong.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilter {

    private long    categoryId;
    private String  query;
    private int     sortType;

    public void setC(long c) {
        this.categoryId = c;
    }
    public void setQ(String q) {
        this.query = q;
    }
    public void setS(int s) {
        this.sortType = s;
    }
}
