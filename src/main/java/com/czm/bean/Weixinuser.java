package com.czm.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mac on 17/7/31.
 */
@Entity
@Getter
@Setter
public class Weixinuser {
    private Integer subscribe;
    @Id
    private String  openid;
    private String  nickname;
    private Integer sex;
    private String  language;
    private String  city;
    private String  province;
    private String  country;
    private String  headimgurl;
    private String  unionid;
    private String remark;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;//创建时间
}
