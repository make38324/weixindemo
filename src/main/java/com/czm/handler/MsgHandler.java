package com.czm.handler;

import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;
import com.github.sd4324530.fastweixin.message.req.ReqType;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.turing.util.PostServer;
import org.aspectj.bridge.MessageHandler;
import org.springframework.stereotype.Component;

/**
 * Created by mac on 17/7/29.
 */
@Component("MsgHandler")
public class MsgHandler implements MessageHandle<BaseReqMsg> {
    @Override
    public BaseMsg handle(BaseReqMsg baseReqMsg) {
        TextReqMsg msg= (TextReqMsg) baseReqMsg;
        String content = msg.getContent();
        String result = PostServer.sendmsg(content);
        System.out.println("msg:"+result);
        return new TextMsg(result);
    }

    @Override
    public boolean beforeHandle(BaseReqMsg baseReqMsg) {
        if(baseReqMsg.getMsgType().equals(ReqType.TEXT)){
            return true;
        }else {
            return false;
        }
    }
}
