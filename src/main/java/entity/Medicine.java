package entity;

public class Medicine {
    private int id_medicine;
    private String medicine_name;
    private String default_dosage;
    private String frequency;
    private int days;
    private int id_prescription;
    private Prescription prescription;

    public Medicine(int id_medicine, String medicine_name, String default_dosage, String frequency, int days, int id_prescription, Prescription prescription) {
        this.id_medicine = id_medicine;
        this.medicine_name = medicine_name;
        this.default_dosage = default_dosage;
        this.frequency = frequency;
        this.days = days;
        this.id_prescription = id_prescription;
        this.prescription = prescription;
    }

    public Medicine(int idMedicine, String medicineName, String defaultDosage, String frequency, int days) {
        this.id_medicine = idMedicine;
        this.medicine_name = medicineName;
        this.default_dosage = defaultDosage;
        this.frequency = frequency;
        this.days = days;
    }

    public Medicine(String medicine_name, String default_dosage, String frequency, int days, int id_prescription, Prescription prescription) {
        this.medicine_name = medicine_name;
        this.default_dosage = default_dosage;
        this.frequency = frequency;
        this.days = days;
        this.id_prescription = id_prescription;
        this.prescription = prescription;
    }

    public int getId_medicine() {
        return id_medicine;
    }

    public void setId_medicine(int id_medicine) {
        this.id_medicine = id_medicine;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getDefault_dosage() {
        return default_dosage;
    }

    public void setDefault_dosage(String default_dosage) {
        this.default_dosage = default_dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getId_prescription() {
        return id_prescription;
    }

    public void setId_prescription(int id_prescription) {
        this.id_prescription = id_prescription;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
