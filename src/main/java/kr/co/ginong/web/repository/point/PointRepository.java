package kr.co.ginong.web.repository.point;

import kr.co.ginong.web.entity.point.Point;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointRepository {

    void updatePoint(Point updatedPointInfo);
}
