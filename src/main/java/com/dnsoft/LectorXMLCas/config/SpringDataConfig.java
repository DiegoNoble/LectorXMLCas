package com.dnsoft.LectorXMLCas.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.dnsoft.LectorXMLCas.daos")
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
@ComponentScan(basePackages = "com.dnsfot.LectorXMLCas")
public class SpringDataConfig {

	@Value(value = "${jdbc.user}")
	private String username;
	@Value(value = "${jdbc.pass}")
	private String password;
	@Value(value = "${jdbc.driver}")
	private String driver;
	@Value(value = "${jdbc.url}")
	private String url;
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(factory);
		manager.setJpaDialect(new HibernateJpaDialect());
		return manager;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(adapter);
		factory.setPackagesToScan("com.dnsoft.LectorXMLCas.beans");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		return dataSource;
	}
}
