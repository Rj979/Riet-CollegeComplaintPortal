package com.portal.dao;

import com.portal.model.Complaint;
import com.portal.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    // Add complaint (student)
    public boolean addComplaint(Complaint complaint) {
        String query = "INSERT INTO complaints (studentName, category, description, status) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, complaint.getStudentName());
            ps.setString(2, complaint.getCategory());
            ps.setString(3, complaint.getDescription());
            ps.setString(4, complaint.getStatus());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all complaints (admin)
    public List<Complaint> getAllComplaints() {
        List<Complaint> list = new ArrayList<>();
        String query = "SELECT * FROM complaints ORDER BY date DESC";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Complaint c = new Complaint();
                c.setId(rs.getInt("id"));
                c.setStudentName(rs.getString("studentName"));
                c.setCategory(rs.getString("category"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getString("status"));
                c.setDate(rs.getTimestamp("date"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Resolve complaint (admin)
    public boolean resolveComplaint(int id) {
        String query = "UPDATE complaints SET status = 'Resolved' WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}