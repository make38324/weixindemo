package com.czm.dao;

import com.czm.bean.Weixinuser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface WeixinuserDao extends JpaRepository<Weixinuser, Long> {

}