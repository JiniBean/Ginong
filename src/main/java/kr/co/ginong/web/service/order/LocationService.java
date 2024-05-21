package kr.co.ginong.web.service.order;

import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.LocationHistory;

import java.util.List;

public interface LocationService {
    Location getByMemberID(Long memberId);

    List<Location> getListByMemberID(Long memberId);

    Location getByID(Long id);
    LocationHistory getHistoryByOrderID(Long orderId);
    void addHistory(LocationHistory locationHistory);

    Integer removeLocationById(Long locationId);

    Integer addLocation(Location location);
}
