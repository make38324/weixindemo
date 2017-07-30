package com.czm.handler;

import com.czm.api.WeixinController;
import com.czm.dao.WeixinuserDao;
import com.czm.utils.ParseUtil;
import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
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
        //TODO 获取用户信息
        ApiConfig apiConfig=new ApiConfig(WeixinController.getAPPID(),WeixinController.getAPPSECRET());
        UserAPI userAPI=new UserAPI(apiConfig);
        GetUserInfoResponse userInfo = userAPI.getUserInfo(baseEvent.getFromUserName());
        weixinuserDao.save(ParseUtil.parseUser(userInfo));
        return new TextMsg("感谢您，关注Jackcao成功");
    }

    @Override
    public boolean beforeHandle(BaseEvent baseEvent) {
        System.out.println("event:"+baseEvent.getEvent());
        if(baseEvent.getEvent()== EventType.SUBSCRIBE){
            return true;
        }else {
            return false;
        }
    }
}
