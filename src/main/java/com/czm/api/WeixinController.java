package com.czm.api;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.czm.handler.MsgHandler;
import com.czm.handler.SubscribeHandler;
import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.*;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.ReqType;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: WeixinController
 * @date 2017年6月5日 下午9:23:31
 */
@RestController
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {
    private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
    private static final String TOKEN = "jame";
    private static final String APPID = "wx4409ac4d64290eac";
    private static final String APPSECRET = "61e128d4a18aaea7bc08b9fbe1db484a";

    public static String getTOKEN() {
        return TOKEN;
    }

    public static String getAPPID() {
        return APPID;
    }

    public static String getAPPSECRET() {
        return APPSECRET;
    }

    @Autowired
    private SubscribeHandler subscribeHandler;
    @Autowired
    private MsgHandler msgHandler;

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

    //重写父类方法，处理对应的微信消息 返回null则表示不处理
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        log.debug("用户发送到服务器的内容:{}", content);
        System.out.println("content:" + content);
        return null;
    }

    @Override
    protected BaseMsg handleSubscribe(BaseEvent event) {
        //交给handler
        return null;
    }

    /*1.1版本新增，重写父类方法，加入自定义微信消息处理器
             *不是必须的，上面的方法是统一处理所有的文本消息，如果业务觉复杂，上面的会显得比较乱
             *这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
             */
    @Override
    protected List<MessageHandle> initMessageHandles() {
        List<MessageHandle> handles = new ArrayList<MessageHandle>();
        handles.add(msgHandler);
        return handles;
    }

    //1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
    @Override
    protected List<EventHandle> initEventHandles() {
        List<EventHandle> handles = new ArrayList<EventHandle>();
        //关注事件处理类添加进来
        handles.add(subscribeHandler);
        return handles;
    }

    @RequestMapping("/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        ApiConfig apiConfig = new ApiConfig(getAPPID(), getAPPSECRET());
        OauthAPI oauthAPI = new OauthAPI(apiConfig);
        System.out.println("code"+code);
        if(code ==null) {
            String wxauthurl = oauthAPI.getOauthPageUrl("http://m.xuli.bid/weixin/auth", OauthScope.SNSAPI_USERINFO, "123");
            response.sendRedirect(wxauthurl);
            return;
        }else{
            OauthGetTokenResponse token = oauthAPI.getToken(code);
            String openid = token.getOpenid();
            System.out.println("getopenid():"+openid);
            response.sendRedirect("http://m.xuli.bid/wx/index?openid="+openid);
            return;
        }
    }
}