package com.czm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/10.
 */
@Entity
@Getter
@Setter
public class Log {
    public static final String TYPE_USER_LOGIN="用户登录";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long log_id;
    private String username;
    private String IP;
    private String URL;
    private String operation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationtime;
}
