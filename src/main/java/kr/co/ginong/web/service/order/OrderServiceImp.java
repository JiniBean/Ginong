package kr.co.ginong.web.service.order;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import kr.co.ginong.web.entity.order.OrderCategory;
import kr.co.ginong.web.repository.order.CancelRepository;
import kr.co.ginong.web.repository.order.OrderCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ginong.web.entity.order.Order;
import kr.co.ginong.web.entity.order.OrderItem;
import kr.co.ginong.web.entity.order.OrderView;
import kr.co.ginong.web.repository.order.OrderItemRepository;
import kr.co.ginong.web.repository.order.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    private OrderCategoryRepository categoryRepository;

    @Autowired
    private CancelRepository cancelRepository;

    @Override
    public List<OrderView> getList(Long memberId, String query, Boolean sort) {
        return repository.findAll(memberId, query, sort);
    }

    @Override
    public List<OrderView> getCancelList(Long memberId, String query) {
        return repository.findCancel(memberId, query);
    }


    @Override
    public List<OrderView> getExchangeList(Long memberId, String query, Integer sort) {
        return repository.findExchange(memberId,query,sort);
    }



    @Override
    public List<OrderView> getRefundList(Long memberId, String query, Boolean sort) {
        return repository.findRefund(memberId,query,sort);
    }

    @Override
    public List<OrderView> getItems(Long orderId) {
        return repository.findItems(orderId);
    }

    @Override
    public List<OrderView> getItemsByCancelId(Long cancelId) {
        return repository.findItemsByCancelId(cancelId);
    }
    @Override
    public OrderView getByExchangeId(Long exchangeId) {
        return repository.findByExchangeId(exchangeId);
    }

    @Override
    public OrderView getByRefundId(Long refundId) {
        return repository.findByRefundId(refundId);
    }


    @Override
    public List<OrderCategory> getCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public boolean add(Order order) {
       boolean save = repository.save(order);

        return save;

    }


    @Override
    public boolean addItems(List<OrderItem> items) {
        boolean save = itemRepository.save(items);
        return save;
    }

    @Override
    public Boolean edit(Order order) {
        return repository.update(order);
    }


    @Override
    public Integer getCountOrder(Long memberId) {return  repository.countByMemberId(memberId);}

    @Override
    public Order getRecentOrder(Long memberId) {return repository.findRecentByMemberId(memberId);}

    @Override
    @Transactional
    public boolean addCancel(Order order) {

        //난수 제작 :날짜+4자리난수+4자리난수
        //오늘 날짜 제작
        LocalDate currentDate = LocalDate.now();

        // 날짜를 문자열로 변환하기 (yyyyMMdd 형식)
        String dateString = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //난수 두세트 제작
        Random random = new Random();
        int randomNum1 = random.nextInt(10000);
        int randomNum2 = random.nextInt(10000);
        String randomNumber1 = String.format("%04d", randomNum1);
        String randomNumber2 = String.format("%04d", randomNum2);

        String str = dateString + randomNumber1 + randomNumber2;
        Long cancelId = Long.parseLong(str);

        boolean valid = cancelRepository.save(cancelId);

        if(valid){
            order.setCancelId(cancelId);
            return repository.update(order);
        }

        return false;
    }


}
