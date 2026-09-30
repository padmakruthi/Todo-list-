package todo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TaskCompletedServlet")
public class TaskCompletedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String taskIdStr = request.getParameter("taskid");
        String regIdStr = request.getParameter("regid");

        if (taskIdStr != null && regIdStr != null) {
            try {
                int taskId = Integer.parseInt(taskIdStr);
                int regId = Integer.parseInt(regIdStr);

                RegisterDAO dao = RegisterDAOImpl.getInstance();
                dao.taskCompleted(taskId, regId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("dashboard.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
