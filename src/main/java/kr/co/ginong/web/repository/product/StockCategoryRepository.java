package kr.co.ginong.web.repository.product;

import kr.co.ginong.web.entity.product.StockCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockCategoryRepository {

    List<StockCategory> findAll();
}
