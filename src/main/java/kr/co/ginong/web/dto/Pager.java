package kr.co.ginong.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
    private int page = 1;
    private int size = 20;

    public void setR(int r) {
        this.size = r;
    }

    public void setP(int p) {
        this.page = p;
    }

    public int getOffset() {
        return (page - 1) * size;
    }
}
