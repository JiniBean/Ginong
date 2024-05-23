package kr.co.ginong.web.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class WebUserDetails implements UserDetails, OAuth2User {

    private Long    id;
    private String  name;
    private String  username;
    private String  email;
    private String  password;
    private String  phone;
    private boolean state;
    private List<GrantedAuthority> authorities;
    private Map<String, Object> attributes;


    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    @Override
    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    @Override
    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public boolean isState() {return state;}

    public void setState(boolean state) {this.state = state;}


    public void setAuthorities(List<GrantedAuthority> authorities) {this.authorities = authorities;}


    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return authorities;}





    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}
