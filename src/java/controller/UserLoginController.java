package controller;

import dao.UserLoginDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserLoginModel;
import org.json.JSONArray;


public class UserLoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("user-login");
        String password = request.getParameter("password-hash");

        UserLoginModel u = new UserLoginModel(user, password);

        UserLoginDAO udao = new UserLoginDAO();
        JSONArray jsonArray = udao.loginUser(u);
        
        if (jsonArray != null){
            request.getSession().setAttribute("email", request.getParameter("user-login"));
        }
        
        response.setContentType("application/json");
        
        response.getWriter().write(jsonArray.toString());
    }
}