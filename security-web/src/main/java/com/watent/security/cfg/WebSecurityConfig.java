package com.watent.security.cfg;

import com.watent.log.filter.LogFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.annotation.Resource;

@EnableWebSecurity
@ConfigurationProperties
public class WebSecurityConfig {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private LogFilter logFilter;

    @Value("${atom.login.url}")
    private String loginUrl;

    @Value("${atom.log.key}")
    private String key;


    @Configuration
    public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/to-login", "/login/**", "/to-login/**", "/home/**", "/").permitAll()
                    .antMatchers("/**").hasRole("ADMIN")
                    .and()
                    .httpBasic()
                    .and()
                    .formLogin()
                    .loginPage(loginUrl)
                    .and()
                    .logout()
                    .permitAll()
                    .and()
                    .exceptionHandling().accessDeniedHandler(accountAccessDeniedHandler());


            http.addFilterAfter(logFilter, FilterSecurityInterceptor.class);
        }
    }


    @Resource
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("USER");
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AccountUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("key", userDetailsService);
    }

    @Bean
    public AccountAccessDeniedHandler accountAccessDeniedHandler() {
        return new AccountAccessDeniedHandler();
    }

}