package kr.co.ginong.web.service.order;

import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.LocationHistory;
import kr.co.ginong.web.repository.order.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImp implements LocationService{

    @Autowired
    LocationRepository repository;

    @Override
    public void addHistory(LocationHistory locationHistory) {
        repository.saveHistory(locationHistory);

    }

    @Override
    public Location getByMemberID(Long memberId) {
        Location location = repository.findByMemberId(memberId);
        return location;
    }

    @Override
    public Location getByID(Long id) {
        Location location = repository.findById(id);
        return location;
    }

    @Override
    public LocationHistory getByOrderID(Long orderId) {
        LocationHistory locationHistory = repository.findByOrderId(orderId);
        return locationHistory;
    }

    @Override
    public List<Location> getListByMemberID(Long memberId) {
        List<Location> list = repository.findListByMemberId(memberId);
        return list;
    }

    @Override
    public Integer removeLocationById(Long locationId) {
        int state =  repository.deleteLocation(locationId);
        System.out.println(state);

        return state;
    }





}
