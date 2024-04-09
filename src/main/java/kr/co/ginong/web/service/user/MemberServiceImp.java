package kr.co.ginong.web.service.user;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.order.Location;
import kr.co.ginong.web.entity.order.LocationHistory;
import kr.co.ginong.web.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService{

    @Autowired
    private MemberRepository repository;


    @Override
    public Member get(String name) {
        Member member = repository.find(null,name);
        return member;
    }

    @Override
    public Member get(Long id) {
        Member member = repository.find(id,null);
        return member;
    }



}
