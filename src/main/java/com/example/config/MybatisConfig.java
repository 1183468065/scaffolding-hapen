package com.example.config;

import com.example.pagination.PaginationInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        Properties properties = new Properties();
        properties.put("dbms", "mysql");
        properties.put("sqlRegex", ".*WithRowbounds*");
        paginationInterceptor.setProperties(properties);
        Interceptor[] plugins = {paginationInterceptor};
        sqlSessionFactory.setPlugins(plugins);
        return sqlSessionFactory;
    }

}
