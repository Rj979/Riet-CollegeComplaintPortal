package com.portal.servlets;

import com.portal.dao.ComplaintDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/resolveComplaint")
public class ResolveComplaintServlet extends HttpServlet {
    private final ComplaintDAO cdao = new ComplaintDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            boolean ok = cdao.resolveComplaint(id);
            if (ok) resp.setStatus(HttpServletResponse.SC_OK);
            else resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}