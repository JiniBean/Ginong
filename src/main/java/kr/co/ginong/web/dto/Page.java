package kr.co.ginong.web.dto;

import kr.co.ginong.web.entity.product.ProductView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Page {
    private int count;

    private List<ProductView> list;
}
