package entity;

public class Doctor {
    private int id_doctor;
    private String name;
    private String specialty;// Chuyên khoa
    private String qualification;// chuyên môn
    private String phone;

    public Doctor(int id_doctor, String name, String specialty, String qualification, String phone) {
        this.id_doctor = id_doctor;
        this.name = name;
        this.specialty = specialty;
        this.qualification = qualification;
        this.phone = phone;
    }

    public Doctor(String name, String specialty, String qualification, String phone) {
        this.name = name;
        this.specialty = specialty;
        this.qualification = qualification;
        this.phone = phone;
    }

    public Doctor(int id_doctor, String name) {
        this.id_doctor = id_doctor;
        this.name = name;
    }

    public Doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
