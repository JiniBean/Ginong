package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.point.Point;
import kr.co.ginong.web.entity.point.PointHistoryView;
import kr.co.ginong.web.service.point.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("apiPointController")
@RequestMapping("api/point")
public class PointController {

    @Autowired
    private PointService pointService;


    @GetMapping("history/list")
    public List<PointHistoryView> list(
            @RequestParam(name = "id", required = false) Long memberId
    ) {

        List<PointHistoryView> pointHistoryViewList = new ArrayList<>();

        pointHistoryViewList = pointService.getList(memberId);

        return pointHistoryViewList;

    }

    @GetMapping("history/detail/{id}")
    public PointHistoryView detail(@PathVariable(name = "id") Long id) {

        return pointService.getById(id);
    }




//
//    @GetMapping("payment/list")
//    public List<PointHistoryView> paymentList(@RequestParam(required = false) String query){
//
//        return pointService.getpaymentList(query);
//    }
}
