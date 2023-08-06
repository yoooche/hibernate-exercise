package core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static core.util.HibernateUtil.*;

@WebListener
public class HibernateListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		getSessionFactory();
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		shutdown();
	}
	
	public static void main(String[] args) {
		System.out.println("yyy");
	}
}
