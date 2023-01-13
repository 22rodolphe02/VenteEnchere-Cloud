package com.enchere.config.base;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.enchere.mongo.repos",
        entityManagerFactoryRef = "mongoEntityManagerFactory",
        transactionManagerRef = "mongoTransactionManager")
public class MongoConfig {

    @Bean(name = "mongoDatasource")
    @ConfigurationProperties(prefix = "spring.datasource-secondary")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mongoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("mongoDatasource") DataSource dataSource) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.enchere.mongo.models")
                        .persistenceUnit("Mongo")
                        .build();
    }
    @Bean(name = "mongoTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("mongoEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
