package controller;

import dao.UserLoginDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
        
        
        request.getSession().setAttribute("user-id", jsonArray.getJSONObject(0).get("id").toString());
        
        
        response.setContentType("application/json");
        response.getWriter().write(jsonArray.toString());
    }
}