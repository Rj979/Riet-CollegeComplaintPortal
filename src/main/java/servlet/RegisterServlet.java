package com.portal.servlets;

import com.portal.dao.UserDAO;
import com.portal.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserDAO dao = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");

        // If username wasn't provided in your HTML, fallback to email or fullname as username
        if (username == null || username.trim().isEmpty()) {
            if (email != null && !email.trim().isEmpty()) username = email.split("@")[0];
            else username = fullname.replaceAll("\\s+","").toLowerCase();
        }

        User u = new User();
        u.setFullname(fullname);
        u.setEmail(email);
        u.setUsername(username);
        u.setPassword(password);
        u.setRole("student");

        boolean ok = dao.createUser(u);
        if (ok) {
            // On success redirect to student login page
            resp.sendRedirect("studentLogin.html?registered=1");
        } else {
            resp.sendRedirect("studentLogin.html?error=register");
        }
    }
}