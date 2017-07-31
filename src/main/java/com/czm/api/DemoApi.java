package com.czm.api;

import com.github.sd4324530.fastweixin.api.QrcodeAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.QrcodeType;
import com.github.sd4324530.fastweixin.api.response.QrcodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.sd4324530.fastweixin.api.enums.QrcodeType.QR_LIMIT_SCENE;

/**
 * Created by mac on 17/7/29.
 */
@RestController
@RequestMapping("/api/demo")
public class DemoApi {
    @GetMapping(value = "test")
    public String test(){
        return "hello world";
    }
    @GetMapping(value = "getQrcode")
    public QrcodeResponse getQrcode(@RequestParam int shopid){
        ApiConfig apiConfig=new ApiConfig(WeixinController.getAPPID(),WeixinController.getAPPSECRET());
        QrcodeAPI qrcodeAPI=new QrcodeAPI(apiConfig);
        QrcodeResponse qrcode = qrcodeAPI.createQrcode(QrcodeType.QR_LIMIT_SCENE, shopid + "", 2592000);
        return qrcode;
    }
}
