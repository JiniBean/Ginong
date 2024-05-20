package kr.co.ginong.web.controller.api;

import kr.co.ginong.web.entity.point.PointHistoryView;
import kr.co.ginong.web.service.point.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("apiPointController")
@RequestMapping("api/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @GetMapping("list")
    public List<PointHistoryView> list(
            @RequestParam(name = "id", required = false) Long memberId
    ) {

        List<PointHistoryView> pointHistoryViewList = new ArrayList<>();

        pointHistoryViewList = pointService.getList(memberId);

        return pointHistoryViewList;

    }

}
