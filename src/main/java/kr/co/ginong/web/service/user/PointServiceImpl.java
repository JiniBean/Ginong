package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.point.PointHistoryView;
import kr.co.ginong.web.repository.point.PointHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl {

    @Autowired
    PointHistoryRepository repository;


    List<PointHistoryView> getList(Long memberId) {

        List<PointHistoryView> list = repository.findAll();

        return list;
    }

    PointHistoryView get(Long memberId) {
        PointHistoryView pointHistoryView = repository.findById(memberId);
        return null;
    }


}
