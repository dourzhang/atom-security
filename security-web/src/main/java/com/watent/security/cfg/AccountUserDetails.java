package com.watent.security.cfg;

import com.watent.security.controller.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountUserDetails extends Account implements UserDetails {

    private List<GrantedAuthority> grantedAuthorities;

    public AccountUserDetails(Account account) {
        setId(account.getId());
        setNickName(account.getNickName());
        setCreatedAt(account.getCreatedAt());
        setUpdatedAt(account.getUpdatedAt());

        this.grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(account.getAuthority());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
