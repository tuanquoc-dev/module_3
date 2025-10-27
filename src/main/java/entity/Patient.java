package entity;

import java.util.Date;

public class Patient {
    private int id_patient;
    private String name;
    private Date birth_date;
    private String gender;
    private String phone;
    private String address;

    public Patient(int id_patient, String name, Date birth_date, String gender, String phone, String address) {
        this.id_patient = id_patient;
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public Patient(String name, Date birth_date, String gender, String phone, String address) {
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public Patient(int id_patient, String name) {
        this.id_patient = id_patient;
        this.name = name;
    }

    public Patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
