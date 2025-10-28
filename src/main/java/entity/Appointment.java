package entity;

import java.util.Date;

public class Appointment {
    private int id_appointment;
    private Date appointment_date;
    private String status;
    private int id_patient;
    private int id_doctor;
    private Patient  patient;
    private Doctor doctor;

    public Appointment(int id_appointment, Date appointment_date, String status,
                       int id_patient, int id_doctor, Patient patient, Doctor doctor) {
        this.id_appointment = id_appointment;
        this.appointment_date = appointment_date;
        this.status = status;
        this.id_patient = id_patient;
        this.id_doctor = id_doctor;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Appointment(Date appointment_date, String status, int id_patient, int id_doctor, Patient patient, Doctor doctor) {
        this.appointment_date = appointment_date;
        this.status = status;
        this.id_patient = id_patient;
        this.id_doctor = id_doctor;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Appointment(int id_appointment, Date appointment_date, String status) {
        this.id_appointment = id_appointment;
        this.appointment_date = appointment_date;
        this.status = status;
    }

    public Appointment() {
    }

    public Appointment(int idAppointment, Date appointmentDate, String status, Patient patient, Doctor doctor) {
        this.id_appointment = idAppointment;
        this.appointment_date = appointmentDate;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Appointment(int id_appointment, Date appointment_date) {
        this.id_appointment = id_appointment;
        this.appointment_date = appointment_date;
    }

    public Appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
