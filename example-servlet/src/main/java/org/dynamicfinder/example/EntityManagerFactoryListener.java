package org.dynamicfinder.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bitronix.tm.Configuration;
import bitronix.tm.TransactionManagerServices;

@WebListener
public class EntityManagerFactoryListener implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ClassLoader classLoader = sce.getServletContext().getClassLoader();
		final String configFile = classLoader.getResource("bitronix.properties").getPath();

		Configuration config = TransactionManagerServices.getConfiguration();
		config.setServerId("dynamicfinder-server");
		config.setResourceConfigurationFilename(configFile);

		TransactionManagerServices.getTransactionManager();

		entityManagerFactory = Persistence.createEntityManagerFactory("dynamicfinder-PU");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
