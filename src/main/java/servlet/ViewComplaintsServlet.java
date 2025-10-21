package com.portal.servlet;

import com.portal.dao.ComplaintDAO;
import com.portal.model.Complaint;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewComplaintsServlet")
public class ViewComplaintsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ComplaintDAO dao = new ComplaintDAO();
        List<Complaint> complaints = dao.getAllComplaints();
        request.setAttribute("complaints", complaints);
        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
    }
}