package com.czm.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mac on 17/8/16.
 */
public class MybatiFactory {
    public static void main(String[] args){
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//        Environment environment = new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(BlogMapper.class);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

    }
}
