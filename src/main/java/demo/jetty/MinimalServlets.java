package demo.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class MinimalServlets
{
    private static final String KEYSTORE_LOCATION = "src\\main\\resources\\jetty.jks";
    private static final String KEYSTORE_PASS = "password";
    
    public static void main( String[] args ) throws Exception
    {
        startServerSSL();
    }
    
    private static void startServerSSL() throws Exception {
        Server server = new Server();
        SslContextFactory sslContextFactory = new SslContextFactory(KEYSTORE_LOCATION);
        sslContextFactory.setKeyStorePassword(KEYSTORE_PASS);
 
        // create a https connector
        SslSocketConnector connector = new SslSocketConnector(sslContextFactory);
        connector.setPort(8443);
 
        // register the connector
        server.setConnectors(new Connector[] { connector });
 
        ServletContextHandler scHandler = new ServletContextHandler(server,"/");
        scHandler.addServlet(HelloServlet.class, "/");
        server.start();
        server.join();
    }

    private static void startServer() throws Exception {
        // Create a basic jetty server object that will listen on port 8080.
        // Note that if you set this to port 0 then a randomly available port
        // will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(8080);

        // The ServletHandler is a dead simple way to create a context handler
        // that is backed by an instance of a Servlet.
        // This handler then needs to be registered with the Server object.
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        // Passing in the class for the Servlet allows jetty to instantiate an
        // instance of that Servlet and mount it on a given context path.

        // IMPORTANT:
        // This is a raw Servlet, not a Servlet that has been configured
        // through a web.xml @WebServlet annotation, or anything similar.
        handler.addServletWithMapping(HelloServlet.class, "/*");

        // Start things up!
        server.start();

        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See
        // http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
        server.join();
    }

    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet
    {
        @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                                                            IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
        }
    }
}
