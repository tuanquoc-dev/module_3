package entity;

import java.util.Date;

public class Prescription {
    private int id_prescription;
    private Date prescription_date;
    private String note;
    private int id_medical_record;
    private MedicalRecord medicalRecord;

    public Prescription(int id_prescription, Date prescription_date, String note, int id_medical_record, MedicalRecord medicalRecord) {
        this.id_prescription = id_prescription;
        this.prescription_date = prescription_date;
        this.note = note;
        this.id_medical_record = id_medical_record;
        this.medicalRecord = medicalRecord;
    }

    public Prescription(int id_prescription, Date prescription_date, String note) {
        this.id_prescription = id_prescription;
        this.prescription_date = prescription_date;
        this.note = note;
    }

    public Prescription(int id_prescription, Date prescription_date, String note, MedicalRecord medicalRecord) {
        this.id_prescription = id_prescription;
        this.prescription_date = prescription_date;
        this.note = note;
        this.medicalRecord = medicalRecord;
    }

    public Prescription(Date prescription_date, String note, int id_medical_record, MedicalRecord medicalRecord) {
        this.prescription_date = prescription_date;
        this.note = note;
        this.id_medical_record = id_medical_record;
        this.medicalRecord = medicalRecord;
    }

    public Prescription(int id_prescription, Date prescription_date) {
        this.id_prescription = this.id_prescription;
        this.prescription_date = prescription_date;
    }

    public Prescription(int id_prescription) {
        this.id_prescription = id_prescription;
    }

    public int getId_prescription() {
        return id_prescription;
    }

    public void setId_prescription(int id_prescription) {
        this.id_prescription = id_prescription;
    }

    public Date getPrescription_date() {
        return prescription_date;
    }

    public void setPrescription_date(Date prescription_date) {
        this.prescription_date = prescription_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId_medical_record() {
        return id_medical_record;
    }

    public void setId_medical_record(int id_medical_record) {
        this.id_medical_record = id_medical_record;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
