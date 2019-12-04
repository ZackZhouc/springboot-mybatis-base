package com.yzj.cep.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WxController {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    private String baseurl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getSignature")
    public String getSignature() {
        System.out.println(appid);
        System.out.println(secret);
        String url = baseurl + "&appid=" + appid + "&secret=" + secret;

        System.out.println(url);
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }


}
