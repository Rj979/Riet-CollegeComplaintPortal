package com.portal.dao;

import com.portal.model.Complaint;
import com.portal.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    public boolean insertComplaint(Complaint c) {
        String sql = "INSERT INTO complaints (user_id, category, text, status) VALUES (?, ?, ?, 'Pending')";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getUserId());
            ps.setString(2, c.getCategory());
            ps.setString(3, c.getText());
            int a = ps.executeUpdate();
            return a == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Complaint> findByUserId(int userId) {
        String sql = "SELECT * FROM complaints WHERE user_id = ? ORDER BY created_at DESC";
        List<Complaint> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Complaint c = new Complaint();
                    c.setId(rs.getInt("id"));
                    c.setUserId(rs.getInt("user_id"));
                    c.setCategory(rs.getString("category"));
                    c.setText(rs.getString("text"));
                    c.setStatus(rs.getString("status"));
                    c.setCreatedAt(rs.getTimestamp("created_at"));
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Complaint> findAll() {
        String sql = "SELECT * FROM complaints ORDER BY created_at DESC";
        List<Complaint> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Complaint c = new Complaint();
                c.setId(rs.getInt("id"));
                c.setUserId(rs.getInt("user_id"));
                c.setCategory(rs.getString("category"));
                c.setText(rs.getString("text"));
                c.setStatus(rs.getString("status"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean resolveComplaint(int id) {
        String sql = "UPDATE complaints SET status = 'Resolved' WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int a = ps.executeUpdate();
            return a == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}