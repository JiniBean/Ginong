package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.point.PointHistoryView;
import kr.co.ginong.web.repository.point.PointHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService{

    @Autowired
    PointHistoryRepository repository;


    public List<PointHistoryView> getList(Long memberId) {

        List<PointHistoryView> list = repository.findAll(memberId);

        return list;
    }

    @Override
    public int getAvailPoint(Long memberId) {

        int availPoint = repository.calc(memberId);
        return availPoint;
    }


}
