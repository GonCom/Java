package listeners;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.JPADaoFactory;

/**
 * Application Lifecycle Listener implementation class PersistenciaAppListener
 * 
 */
@WebListener
public class PersistenciaAppListener implements ServletContextListener {
	/**
	 * Default constructor.
	 */
	public PersistenciaAppListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(servletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		JPADaoFactory.close();
	}
}