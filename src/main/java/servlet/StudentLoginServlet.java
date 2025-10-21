package com.portal.servlets;

import com.portal.dao.UserDAO;
import com.portal.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class StudentLoginServlet extends HttpServlet {
    private final UserDAO dao = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username"); // from index.html login form
        String password = req.getParameter("password");

        User u = dao.findByUsernameAndPassword(username, password);
        if (u != null && "student".equals(u.getRole())) {
            // store student's username in session or localStorage (frontend used localStorage)
            HttpSession session = req.getSession(true);
            session.setAttribute("username", u.getUsername());
            session.setAttribute("studentFullname", u.getFullname());
            // redirect to afterlogin page
            resp.sendRedirect("afterlogin.html");
        } else {
            resp.sendRedirect("studentLogin.html?error=invalid");
        }
    }
}