package com.czm.api;

import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.QrCodeEvent;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: WeixinController
 * @date 2017年6月5日 下午9:23:31
 */
@RestController
@RequestMapping("/weixin2")
public class WeixinController2 extends WeixinControllerSupport {
    private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
    private static final String TOKEN = "mrkxgfc3o4vxndpw02wpeyeawtfz12lm";
//    private static final String APPID = "wx4409ac4d64290eac";
//    private static final String APPSECRET = "61e128d4a18aaea7bc08b9fbe1db484a";
    private static final String APPID = "wxc4d05db22e576d0a";
    private static final String APPSECRET = "e59bb860b7f5b46c3d5ed657751f7ff6";
    private static final String baseurl="http://wx.xuli.bid/";

    public static String getTOKEN() {
        return TOKEN;
    }

    public static String getAPPID() {
        return APPID;
    }

    public static String getAPPSECRET() {
        return APPSECRET;
    }


    //设置TOKEN，用于绑定微信服务器
    @Override
    protected String getToken() {
        return TOKEN;
    }

    //使用安全模式时设置：APPID
    //不再强制重写，有加密需要时自行重写该方法
    @Override
    protected String getAppId() {
        return APPID;
    }

    //使用安全模式时设置：密钥
    //不再强制重写，有加密需要时自行重写该方法
    @Override
    protected String getAESKey() {
        return null;
    }

    //参数二维码 已关注扫码
    @Override
    protected BaseMsg handleQrCodeEvent(QrCodeEvent event) {
//        String shopid = event.getEventKey();
//        String openid=event.getFromUserName();
//        System.out.println("event.getEventKey():"+ shopid);
//        Article article=new Article("填写发票信息","填写发票信息","http://pic.58pic.com/58pic/12/21/22/54P58PICBkX.jpg", Constact.baseurl+"register.html?openid="+openid+"shopid="+shopid);
//        NewsMsg newsMsg=new NewsMsg();
//        newsMsg.add(article);
//        return newsMsg;
        return null;
    }
    //参数二维码 未关注扫码
    @Override
    protected BaseMsg handleSubscribe(BaseEvent event) {
        //交给handler
//        QrCodeEvent qrcodeevent =(QrCodeEvent) event;
//        String eventKey = qrcodeevent.getEventKey();
//        String shopid=eventKey.split("_")[1];
//        String openid=event.getFromUserName();
//        Article article=new Article("填写发票信息","填写发票信息","http://pic.58pic.com/58pic/12/21/22/54P58PICBkX.jpg", Constact.baseurl+"register.html?openid="+openid+"shopid="+shopid);
//        NewsMsg newsMsg=new NewsMsg();
//        newsMsg.add(article);
//        System.out.println("event.getEventKey():"+ eventKey);
//        return newsMsg;
        return null;
    }

    /*1.1版本新增，重写父类方法，加入自定义微信消息处理器
             *不是必须的，上面的方法是统一处理所有的文本消息，如果业务觉复杂，上面的会显得比较乱
             *这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
             */
    @Override
    protected List<MessageHandle> initMessageHandles() {
        List<MessageHandle> handles = new ArrayList<MessageHandle>();
        return handles;
    }

    //1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
    @Override
    protected List<EventHandle> initEventHandles() {
        List<EventHandle> handles = new ArrayList<EventHandle>();
        //关注事件处理类添加进来
//        handles.add(subscribeHandler);
        return handles;
    }

    @RequestMapping("/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        ApiConfig apiConfig = new ApiConfig(getAPPID(), getAPPSECRET());
        OauthAPI oauthAPI = new OauthAPI(apiConfig);
        if(code ==null) {
            String wxauthurl = oauthAPI.getOauthPageUrl(baseurl+"weixin/auth", OauthScope.SNSAPI_BASE,"");
            System.out.println("wxauthurl"+wxauthurl);
            response.sendRedirect(wxauthurl);
            return;
        }else{
            OauthGetTokenResponse token = oauthAPI.getToken(code);
            String openid = token.getOpenid();
            System.out.println("getopenid():"+openid);
            response.sendRedirect(baseurl+"car.html?openid="+openid);
            return;
        }
    }
}