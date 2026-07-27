package com.codingrecipe.board.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

        // 1. application.yml에 적어둔 DB 연결 정보를 주입
        sessionFactoryBean.setDataSource(dataSource);

        // 2. mybatis-config.xml 및 mapper xml 위치 지정
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));

        return sessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        // 3. Repository가 그토록 찾아 헤매던 SqlSessionTemplate을 직접 빈(Bean)으로 등록!
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}