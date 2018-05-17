package gov.hrm.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	private @Value("${hibernate.dialect}") String hibernateDialect;
	private @Value("${hibernate.show_sql}") String hibernateShow_sql;
	private @Value("${hibernate.cglib.use_reflection_optimizer}") String hibernateCglibUuse_reflection_optimizer;
	private @Value("${hibernate.jdbc.use_get_generated_keys}") String hibernateJdbcUse_get_generated_keys;

	@Autowired
	private DatabaseConfigHelper databaseConfigHelper;

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		List<ClassPathResource> resources = new ArrayList<ClassPathResource>(4);
		resources.add(new ClassPathResource("application.properties"));
		resources.add(new ClassPathResource("database.properties"));
		ClassPathResource[] resourceArr = new ClassPathResource[resources.size()];
		resources.toArray(resourceArr);
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setLocations(resourceArr);
		propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setMappingResources(getMappingFiles());
		em.setJpaProperties(additionalProperties());
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		return em;
	}
	
	@Bean(destroyMethod = "close")
	public javax.sql.DataSource dataSource() {

		return databaseConfigHelper.buildDataSource();
	}
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.show_sql", hibernateShow_sql);
		properties.put("hibernate.cglib.use_reflection_optimizer", hibernateCglibUuse_reflection_optimizer);
		properties.put("hibernate.jdbc.use_get_generated_keys", hibernateJdbcUse_get_generated_keys);
		return properties;
	}

	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) throws IOException {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	
	@Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }

	private String[] getMappingFiles() throws IOException {

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		org.springframework.core.io.Resource[] resourceArr = resolver.getResources("classpath*:gov/nyaws/model/*.hbm.xml");
		String[] resources = new String[resourceArr.length];
		for (int i = 0; i < resourceArr.length; i++) {
			resources[i] = "gov/nyaws/model/" + resourceArr[i].getFilename();
		}
		return resources;

	}
}
