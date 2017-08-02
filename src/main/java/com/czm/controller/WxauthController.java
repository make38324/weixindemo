package com.czm.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mac on 17/8/2.
 */
@Controller
@RequestMapping("/wx")
public class WxauthController {
    @RequestMapping("/index")
    public String index(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
        String openid = request.getParameter("openid");
        if(openid ==null|| openid.equals("")){
            response.sendRedirect("http://m.xuli.bid/weixin/auth");
        }
        model.addAttribute("openid",openid);
        return "index";
    }
}
