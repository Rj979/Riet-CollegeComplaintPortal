package com.portal.model;

import java.sql.Timestamp;

public class Complaint {
    private int id;
    private String studentName;
    private String category;
    private String description;
    private String status;
    private Timestamp date;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }
}