package com.czm.handler;

import com.czm.api.WeixinController;
import com.czm.dao.WeixinuserDao;
import com.czm.utils.ParseUtil;
import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.EventType;
import com.github.sd4324530.fastweixin.message.req.ReqType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mac on 17/7/29.
 */
@Component("subscribeHandler")
public class SubscribeHandler implements EventHandle<BaseEvent> {

    @Autowired
    WeixinuserDao weixinuserDao;
    @Override
    public BaseMsg handle(BaseEvent baseEvent) {
        //TODO 返回图文消息 url跳转地址 NewsMsg add Article
        Article article=new Article("title","description","http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E9%AB%98%E6%B8%85%E5%8A%A8%E6%BC%AB&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=2520797183,2945766890&os=3521773658,3972744653&simid=4228708105,738849761&pn=5&rn=1&di=58831168990&ln=1973&fr=&fmq=1462357247335_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=10&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F1f178a82b9014a909461e9baa1773912b31bee5e.jpg&rpstart=0&rpnum=0&adpicid=0","http://www.baidu.com");
        Article article2=new Article("title2","description2","http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E9%AB%98%E6%B8%85%E5%8A%A8%E6%BC%AB&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=1824448101,1598387002&os=3967727564,208732275&simid=4252036237,750474182&pn=11&rn=1&di=41106239790&ln=1973&fr=&fmq=1462357247335_R&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=10&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3Dca1fd2eb054f78f0805e92f74c012663%2Fbd3eb13533fa828b97ecd15cfb1f4134960a5a45.jpg&rpstart=0&rpnum=0&adpicid=0","http://www.sina.com");
        NewsMsg newsMsg=new NewsMsg();
        newsMsg.add(article);
        newsMsg.add(article2);
        //TODO 获取用户信息
        ApiConfig apiConfig=new ApiConfig(WeixinController.getAPPID(),WeixinController.getAPPSECRET());
        UserAPI userAPI=new UserAPI(apiConfig);
        GetUserInfoResponse userInfo = userAPI.getUserInfo(baseEvent.getFromUserName());
        weixinuserDao.save(ParseUtil.parseUser(userInfo));
        return newsMsg;
    }

    @Override
    public boolean beforeHandle(BaseEvent baseEvent) {
        System.out.println("event:"+baseEvent.getEvent());
        if(baseEvent.getEvent().equals(EventType.SUBSCRIBE)){
            return true;
        }else {
            return false;
        }
    }
}
