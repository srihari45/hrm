package gov.hrm.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import gov.hrm.utils.StringUtils;

/**
 * 
 * DataBase Helper for building DataSource by taking the properties from properties file
 *
 */
@Component
@Configuration
public class DatabaseConfigHelper {

	private @Value("${db.driverClassName}") String driverClassName;
	private @Value("${db.url}") String url;
	private @Value("${db.username}") String username;
	private @Value("${db.password}") String password;
	private @Value("${db.initialSize}") String initialSize;
	private @Value("${db.maxActive}") String maxTotal;
	private @Value("${db.minIdle}") String minIdle;
	private @Value("${db.maxIdle}") String maxIdle;
	private @Value("${db.defaultAutoCommit}") String defaultAutoCommit;
	private @Value("${db.defaultTransactionIsolation}") String defaultTransactionIsolation;
	private @Value("${db.maxWaitMillis}") String maxWaitMillis;
	private @Value("${db.timeBetweenEvictionRunMillis}") String timeBetweenEvictionRunMillis;
	private @Value("${db.minEvictableIdleTimeMillis}") String minEvictableIdleTimeMillis;
	private @Value("${db.testOnBorrow}") String testOnBorrow;
	private @Value("${db.testOnReturn}") String testOnReturn;
	private @Value("${db.testWhileIdle}") String testWhileIdle;
	private @Value("${db.removeAbandoned}") String removeAbandoned;
	private @Value("${db.removeAbandonedTimeout}") String removeAbandonedTimeout;
	private @Value("${db.validationQuery}") String validationQuery;
	
	public DataSource buildDataSource() {
		
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		if (!StringUtils.isNull(initialSize)) {
			dataSource.setInitialSize(Integer.parseInt(initialSize));
		}
		if (!StringUtils.isNull(maxTotal)) {
			dataSource.setMaxActive(Integer.parseInt(maxTotal));
		}
		if (!StringUtils.isNull(minIdle)) {
			dataSource.setMinIdle(Integer.parseInt(minIdle));
		}
		if (!StringUtils.isNull(maxIdle)) {
			dataSource.setMaxIdle(Integer.parseInt(maxIdle));
		}
		if (!StringUtils.isNull(defaultAutoCommit)) {
			dataSource.setDefaultAutoCommit(Boolean.valueOf(defaultAutoCommit));
		}
		if (!StringUtils.isNull(defaultTransactionIsolation)) {
			dataSource.setDefaultTransactionIsolation(Integer.parseInt(defaultTransactionIsolation));
		}
		if (!StringUtils.isNull(maxWaitMillis)) {
			dataSource.setMaxWait(Integer.parseInt(maxWaitMillis));
		}
		if (!StringUtils.isNull(timeBetweenEvictionRunMillis)) {
			dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunMillis));
		}
		if (!StringUtils.isNull(minEvictableIdleTimeMillis)) {
			dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis));
		}
		if (!StringUtils.isNull(testOnBorrow)) {
			dataSource.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
		}
		if (!StringUtils.isNull(testOnReturn)) {
			dataSource.setTestOnReturn(Boolean.valueOf(testOnReturn));
		}
		if (!StringUtils.isNull(testWhileIdle)) {
			dataSource.setTestWhileIdle(Boolean.valueOf(testWhileIdle));
		}
		if (!StringUtils.isNull(removeAbandoned)) {
			dataSource.setRemoveAbandoned(Boolean.valueOf(removeAbandoned));
		}
		if (!StringUtils.isNull(removeAbandonedTimeout)) {
			dataSource.setRemoveAbandonedTimeout(Integer.parseInt(removeAbandonedTimeout));
		}
		if (!StringUtils.isNull(validationQuery)) {
			dataSource.setValidationQuery(validationQuery);
		}
		dataSource.setLogAbandoned(true);
		dataSource.setJdbcInterceptors(
	            "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
	            + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"
	            + "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer");
		return dataSource;
	}
}
