package listeners;


import java.util.Date;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public MySessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent event) {
    	  
    	System.out.println("Nueva Sesión : "  
    	 + event.getSession().getId()+ " at "+ new Date()); 
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    	public void sessionDestroyed(HttpSessionEvent event) {  
    		  
    		// get the destroying session...  
    		  
    		HttpSession session = event.getSession();  
    		  
    		System.out.println("Sesión agotada por inactividad at: " + new Date()  
    		 + session.getId()+ " Liberando recursos...");  		                        
    }
	
}
