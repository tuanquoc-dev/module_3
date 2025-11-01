package entity;

import java.util.Date;

public class MedicalRecord {
    private int id_medical_record;
    private Date exam_date;
    private String symptoms;// triệu chứng
    private String diagnosis; // chuânr đoán
    private String note;
    private int id_appointment;
    private int id_room;
    private Appointment appointment;
    private Room room;

    public MedicalRecord(int id_medical_record, Date exam_date, String symptoms, String diagnosis, String note, int id_appointment, int id_room, Appointment appointment, Room room) {
        this.id_medical_record = id_medical_record;
        this.exam_date = exam_date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.note = note;
        this.id_appointment = id_appointment;
        this.id_room = id_room;
        this.appointment = appointment;
        this.room = room;
    }
    public MedicalRecord() {}

    public MedicalRecord(int id_medical_record, Date exam_date, String symptoms, String diagnosis, String note) {
        this.id_medical_record = id_medical_record;
        this.exam_date = exam_date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.note = note;
    }

    public MedicalRecord(Date exam_date, String symptoms, String diagnosis, String note, int id_appointment, int id_room, Appointment appointment, Room room) {
        this.exam_date = exam_date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.note = note;
        this.id_appointment = id_appointment;
        this.id_room = id_room;
        this.appointment = appointment;
        this.room = room;
    }

    public MedicalRecord(int id_medical_record, Date exam_date) {
        this.id_medical_record = id_medical_record;
        this.exam_date = exam_date;
    }

    public MedicalRecord(int id_medical_record) {
        this.id_medical_record = id_medical_record;
    }

    public int getId_medical_record() {
        return id_medical_record;
    }

    public void setId_medical_record(int id_medical_record) {
        this.id_medical_record = id_medical_record;
    }

    public Date getExam_date() {
        return exam_date;
    }

    public void setExam_date(Date exam_date) {
        this.exam_date = exam_date;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
