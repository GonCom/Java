package dao;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

public class BaseJPADao {
	/**
	 * Default no-arg constructor
	 */
	public BaseJPADao() {
	}

	/**
	 * Returns JPA EntityManager reference.
	 * 
	 * @return
	 */
	public EntityManager getEntityManager() {
		return JPADaoFactory.createEntityManager();
	}
}

