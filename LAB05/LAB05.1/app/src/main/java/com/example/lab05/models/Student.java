package com.example.lab05.models;

public class Student {
    private long _id;
    private String studentName;

    public Student() {

    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Student(long _id, String studentName, String studentClass) {
        this._id = _id;
        this.studentName = studentName;
        this.studentClass = studentClass;
    }

    private String studentClass;
}
