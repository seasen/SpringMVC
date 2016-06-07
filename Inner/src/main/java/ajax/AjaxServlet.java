package ajax;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by seasen on 2015/12/24.
 */
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        System.out.println(username);
        resp.setCharacterEncoding("utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write(username);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
