package com.negi.manager.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("user")
public class User {

    @PrimaryKey("id")
    private String id;
    @Column("email")
    private String email;
    @Column("fullName")
    private String fullName;
    @Column("managerId")
    private String managerId;
    @Column("department")
    private String department;

    public User() {
        super();
    }

    public User(String id, String email, String fullName, String managerId, String department) {
        super();
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.managerId = managerId;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", managerId='" + managerId + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
