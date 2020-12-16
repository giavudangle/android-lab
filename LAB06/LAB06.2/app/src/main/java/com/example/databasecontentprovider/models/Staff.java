package com.example.databasecontentprovider.models;

public class Staff  {
    private int _id;
    private String _name;
    private String _dateOfBirth;

    public Staff(int _id, String _name, String _dateOfBirth) {
        this._id = _id;
        this._name = _name;
        this._dateOfBirth = _dateOfBirth;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_dateOfBirth() {
        return _dateOfBirth;
    }

    public void set_dateOfBirth(String _dateOfBirth) {
        this._dateOfBirth = _dateOfBirth;
    }
}
