import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description  编译命令：javac -encoding UTF-8 -classpath ./servlet-api-2.4.jar .\PrimitiveServlet.java
 * @Author liuxian
 * @Date 2023/11/6 11:51
 **/
public class PrimitiveServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service...");
        PrintWriter out = servletResponse.getWriter();
        out.println("HTTP/1.1 200 OK");
        out.println();
        out.println("Hello, response from PrimitiveServlet");
        out.print("Violets are blue.");
    }

    @Override
    public void destroy() {
        System.out.println("destroy...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }


}
