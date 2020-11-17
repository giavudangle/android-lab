package com.example.lab05.model;

public class Student {
    public String getmStudentId() {
        return mStudentId;
    }

    public String getmStudentName() {
        return mStudentName;
    }

    public String getmStudentClass() {
        return mStudentClass;
    }

    public void setmStudentId(String mStudentId) {
        this.mStudentId = mStudentId;
    }

    public void setmStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }

    public void setmStudentClass(String mStudentClass) {
        this.mStudentClass = mStudentClass;
    }

    public Student(String mStudentId, String mStudentName, String mStudentClass) {
        this.mStudentId = mStudentId;
        this.mStudentName = mStudentName;
        this.mStudentClass = mStudentClass;
    }

    private String mStudentId;
    private String mStudentName;
    private String mStudentClass;

}
