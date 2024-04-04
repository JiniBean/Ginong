package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.order.LocationHistory;
import kr.co.ginong.web.repository.order.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImp implements LocationService{

    @Autowired
    LocationRepository repository;

    @Override
    public void addHistory(LocationHistory locationHistory) {
        repository.saveByLocation(locationHistory);

    }

}
