package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.point.PointHistory;
import kr.co.ginong.web.entity.point.PointHistoryView;

import java.util.List;

public interface PointService {


    List<PointHistoryView> getList(Long memberId);

    int getAvailPoint(Long memberId);


    boolean addHistory(PointHistory pointHistory);
}
