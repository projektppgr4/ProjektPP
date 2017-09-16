package com.taskmgr.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Akai on 2017-03-27.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan("com.taskmgr")
@PropertySource(value = {"classpath:database.properties"})
public class HibernateConfig {


	private Environment environment;

	@Autowired
	public HibernateConfig(Environment environment) {
		this.environment = environment;
	}


	/**
	 * Sets parameters of datasource depends on properties file
	 *
	 * @return configured DataSource
	 */
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("database.driver"));
		dataSource.setUrl(environment.getProperty("database.url"));
		dataSource.setUsername(environment.getProperty("database.username"));
		dataSource.setPassword(environment.getProperty("database.password"));

		return dataSource;
	}

	/**
	 * Sets parameters of hibernate configuration depends on properties file
	 * @return properties with can be use to configure hibernate
	 */
	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

		return properties;
	}

	/**
	 * Create Session factory
	 * @return SessionFactory
	 */
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		lsfb.setPackagesToScan("com.taskmgr.model");
		lsfb.setHibernateProperties(hibernateProperties());
		try {
			lsfb.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lsfb.getObject();
	}

	/**
	 * Create transaction manager
	 * @param sessionFactory    sessionfactory with will be transactional
	 * @return transaction manager
	 */
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

}
