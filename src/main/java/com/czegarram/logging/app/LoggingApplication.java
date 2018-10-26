package com.czegarram.logging.app;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@ComponentScan(basePackages = {"com.czegarram.logging"})
@MapperScan(basePackages = "com.czegarram.logging.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.czegarram.logging")
public class LoggingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoggingApplication.class);
    }

    @Autowired
    private DataSource dataSource;

    @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.czegarram.logging.dto");
        return sessionFactory.getObject();
    }
}
