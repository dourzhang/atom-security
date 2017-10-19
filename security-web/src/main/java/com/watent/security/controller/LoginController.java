package com.watent.security.controller;


import com.watent.security.cfg.AccountAuthenticationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/account")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static AuthenticationManager am = new AccountAuthenticationManager();

    @Resource
    private RememberMeServices rememberMeServices;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                        @RequestParam String username, @RequestParam String password) {

        if (!username.equals("admin") && "123456".equals(password)) {
            model.put("error", "login fail");
            return "login";
        }

        Authentication token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication accountAuthentication = am.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(accountAuthentication);
        rememberMeServices.loginSuccess(request, response, token);

        return "hello";
    }
}