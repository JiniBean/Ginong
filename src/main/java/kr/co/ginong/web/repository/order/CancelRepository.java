package kr.co.ginong.web.repository.order;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CancelRepository {
    Boolean save(Long id);
}
