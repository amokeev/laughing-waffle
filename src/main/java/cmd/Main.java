package cmd;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.api.People;

/**
 * Created by lesha on 27.04.18.
 */
public class Main {
    static final int PORT = 8081;
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(PORT);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                People.class.getCanonicalName());

        try {
            System.out.println("AAAA 1");

            jettyServer.start();
            System.out.println("AAA 2");

            jettyServer.join();
            System.out.println("3");
        } catch (Throwable e) {
            System.out.println("AAA 4");
            e.printStackTrace();
            log.trace("Issue starting server", e);
        }finally {
            jettyServer.destroy();
        }
    }
}
