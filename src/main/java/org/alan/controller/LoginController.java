package org.alan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @program: learn-security-jwt
 * @ClassName: LoginController
 * @description:
 * @author: AlanMa
 * @create: 2019-07-08 13:27
 */
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> body) {

        String userName = body.get("userName");
        String password = body.get("password");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        if (!authentication.isAuthenticated()) {
            return "用户名和密码错误";
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "成功";
    }
}