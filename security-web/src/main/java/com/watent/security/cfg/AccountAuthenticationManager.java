package com.watent.security.cfg;

import com.watent.security.controller.Account;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AccountAuthenticationManager implements AuthenticationManager {

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        AccountUserDetails accountUserDetails = new AccountUserDetails(getAccount());

        return new UsernamePasswordAuthenticationToken(accountUserDetails.getId(),
                auth.getCredentials(), accountUserDetails.getAuthorities());
    }

    private Account getAccount() {
        Account account = new Account();
        account.setId(1001L);
        account.setNickName("administer");
        account.setUsername("admin");
        account.setAuthority("ROLE_ADMIN,ROLE_ANALYZE");
        account.setCreatedAt(account.getCreatedAt());
        account.setUpdatedAt(account.getUpdatedAt());
        return account;
    }
}