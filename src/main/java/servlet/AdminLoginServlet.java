package com.portal.servlets;

import com.portal.dao.UserDAO;
import com.portal.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    private final UserDAO dao = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User u = dao.findByUsernameAndPassword(username, password);
        if (u != null && "admin".equals(u.getRole())) {
            HttpSession session = req.getSession(true);
            session.setAttribute("adminUsername", u.getUsername());
            resp.sendRedirect("adminDashboard.html");
        } else {
            resp.sendRedirect("adminLogin.html?error=invalid");
        }
    }
}