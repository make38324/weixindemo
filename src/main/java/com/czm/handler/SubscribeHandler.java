package com.czm.handler;

import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import org.springframework.stereotype.Component;

/**
 * Created by mac on 17/7/29.
 */
@Component
public class SubscribeHandler implements EventHandle<BaseEvent> {
    @Override
    public BaseMsg handle(BaseEvent baseEvent) {
        return null;
    }

    @Override
    public boolean beforeHandle(BaseEvent baseEvent) {
        return false;
    }
}
