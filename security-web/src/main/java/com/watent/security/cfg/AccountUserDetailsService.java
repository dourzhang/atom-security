package com.watent.security.cfg;

import com.watent.security.controller.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.Random;

public class AccountUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = findAccount(username);
        return new AccountUserDetails(account);
    }

    private Account findAccount(String username) {
        Random random = new Random(100L);
        Account account = new Account();
        account.setId(Math.abs(random.nextLong()) % 10);
        account.setNickName("qqt");
        account.setUsername(username);
        account.setPassword("123456");
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setAuthority("ROLE_ADMIN,ROLE_ANALYZE");
        return account;
    }
}
