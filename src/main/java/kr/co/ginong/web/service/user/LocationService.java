package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.LocationHistory;

public interface LocationService {
    Location getByMemberID(Long memberId);
    void addHistory(LocationHistory locationHistory);
}
