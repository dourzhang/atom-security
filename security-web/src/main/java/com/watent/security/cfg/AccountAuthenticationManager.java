package com.watent.security.cfg;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AccountAuthenticationManager implements AuthenticationManager {

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        AccountUserDetails accountUserDetails = new AccountUserDetails(getAccount());

        return new UsernamePasswordAuthenticationToken(auth.getName(),
                auth.getCredentials(), accountUserDetails.getAuthorities());
    }

    private Account getAccount() {
        Account account = new Account();
        account.setId(account.getId());
        account.setNickName(account.getNickName());
        account.setAuthority("ROLE_ADMIN,ROLE_ANALYZE");
        account.setCreatedAt(account.getCreatedAt());
        account.setUpdatedAt(account.getUpdatedAt());
        return account;
    }
}