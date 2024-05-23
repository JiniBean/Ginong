package kr.co.ginong.web.config.security;

import kr.co.ginong.web.entity.member.Member;
import kr.co.ginong.web.entity.member.MemberRole;
import kr.co.ginong.web.repository.member.MemberRepository;
import kr.co.ginong.web.repository.member.MemberRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebOAuth2UserDetailsService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getAttribute("name");

        Member member = repository.findByEmail(email);

        WebUserDetails userDetails = new WebUserDetails();
        if(member == null) {
            userDetails.setAttributes(oAuth2User.getAttributes());
            userDetails.setName(oAuth2User.getName());
            userDetails.setUsername("임시로그인");
            userDetails.setEmail(email);

            return userDetails;
        }
//        ----------------------security info--------------------
        List<MemberRole> roles = memberRoleRepository.findAllByMemberId(member.getId());

        List<GrantedAuthority> authorities = new ArrayList<>();

        for(MemberRole role : roles) //권한 정보들을 꺼내서 authorities에 넣어준다.
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        userDetails.setId(member.getId());
        userDetails.setUsername(member.getUserName());
        userDetails.setPassword(member.getPwd());
        userDetails.setEmail(member.getEmail());
        userDetails.setPhone(member.getPhone());
        userDetails.setState(member.isState());
        userDetails.setName(member.getName());
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
