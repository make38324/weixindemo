package com.czm.api;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.czm.handler.MsgHandler;
import com.czm.handler.SubscribeHandler;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.ReqType;
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

/**
 *
 * @ClassName: WeixinController
 * @date 2017年6月5日 下午9:23:31
 */
@RestController
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {
        private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
        private static final String TOKEN = "jame";
        private static final String APPID="wxd4b606be1d6bbcc0";
        private static final String APPSECRET="75836260484e42a4a21fe81af956473d";

        @Autowired
        private SubscribeHandler subscribeHandler;
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
                return APPSECRET;
        }
        //重写父类方法，处理对应的微信消息 返回null则表示不处理
        @Override
        protected BaseMsg handleTextMsg(TextReqMsg msg) {
                String content = msg.getContent();
                log.debug("用户发送到服务器的内容:{}", content);
                System.out.println("content:"+content);
                return new TextMsg("hello，哈哈");
        }
        /*1.1版本新增，重写父类方法，加入自定义微信消息处理器
         *不是必须的，上面的方法是统一处理所有的文本消息，如果业务觉复杂，上面的会显得比较乱
         *这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
         */
        @Override
        protected List<MessageHandle> initMessageHandles() {
                List<MessageHandle> handles = new ArrayList<MessageHandle>();
//                handles.add(new MsgHandler());
                return handles;
        }
        //1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
        @Override
        protected List<EventHandle> initEventHandles() {
                List<EventHandle> handles = new ArrayList<EventHandle>();
                //关注事件处理类添加进来
//                handles.add(subscribeHandler);
                return handles;
        }
}