package com.denglitong.readinglist.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/13
 */
@Entity
public class ReaderEntity implements UserDetails {

    private static final long serialVersionUID = -926624239030718029L;

    @Id
    private String username;

    private String fullname;

    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* UserDetails method... */

    // 授予 READER 权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if ("denglitong".equalsIgnoreCase(username)) {
            return Arrays.asList(new SimpleGrantedAuthority("READER"),
                    new SimpleGrantedAuthority("ADMIN"));
        }
        return Arrays.asList(new SimpleGrantedAuthority("READER"));
    }

    // 不过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 不加锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 不禁用
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
