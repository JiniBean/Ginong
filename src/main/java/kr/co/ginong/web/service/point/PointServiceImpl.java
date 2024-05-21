package kr.co.ginong.web.service.point;

import kr.co.ginong.web.entity.point.PointHistory;
import kr.co.ginong.web.entity.point.PointHistoryView;
import kr.co.ginong.web.repository.point.PointHistoryRepository;
import kr.co.ginong.web.repository.point.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService{

//    @Autowired
//    PointRepository repository;

    @Autowired
    PointHistoryRepository historyRepository;


    public List<PointHistoryView> getList(Long memberId) {

        List<PointHistoryView> list = historyRepository.findAll(memberId);

        return list;
    }

    @Override
    public int getAvailPoint(Long memberId) {

        int availPoint = historyRepository.calc(memberId);
        return availPoint;
    }

    @Override
    public boolean addHistory(PointHistory pointHistory) {
        return historyRepository.save(pointHistory);
    }

    @Override
    public PointHistoryView getById(Long id) {

        return historyRepository.findById(id);
    }


}
