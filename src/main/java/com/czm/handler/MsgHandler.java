package com.czm.handler;

import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;
import org.aspectj.bridge.MessageHandler;
import org.springframework.stereotype.Component;

/**
 * Created by mac on 17/7/29.
 */
@Component
public class MsgHandler implements MessageHandle<BaseReqMsg> {
    @Override
    public BaseMsg handle(BaseReqMsg baseReqMsg) {
        return null;
    }

    @Override
    public boolean beforeHandle(BaseReqMsg baseReqMsg) {
        return false;
    }
}
