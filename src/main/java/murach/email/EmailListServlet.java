package murach.email;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import murach.business.User;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

public class EmailListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
        throws ServletException, IOException {
        String url = "/index.html";
//        Get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // Default action
        }
//        Perform action and set Url to approriate page
        if (action.equals("join")) {
            url = "/index.html";
        }
        else if (action.equals("add")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            User user = new User(firstName, lastName, email);
//            UserDB.insert(user);
            request.setAttribute("user", user);
            url ="/thanks.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
