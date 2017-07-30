package com.czm.utils;

import com.czm.bean.Weixinuser;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;

/**
 * Created by mac on 17/7/31.
 */
public class ParseUtil {
    public static Weixinuser parseUser(GetUserInfoResponse userInfoResponse){
        Weixinuser weixinuser=new Weixinuser();
        weixinuser.setOpenid(userInfoResponse.getOpenid());
        weixinuser.setSubscribe(userInfoResponse.getSubscribe());
        weixinuser.setNickname(userInfoResponse.getNickname());
        weixinuser.setSex(userInfoResponse.getSex());
        weixinuser.setLanguage(userInfoResponse.getLanguage());
        weixinuser.setCity(userInfoResponse.getCity());
        weixinuser.setProvince(userInfoResponse.getProvince());
        weixinuser.setHeadimgurl(userInfoResponse.getHeadimgurl());
        weixinuser.setUnionid(userInfoResponse.getUnionid());
        weixinuser.setSubscribe(userInfoResponse.getSubscribe());
        weixinuser.setRemark(userInfoResponse.getRemark());
        return weixinuser;
    }
}
