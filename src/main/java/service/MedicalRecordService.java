package service;

import configuration.MySqlConnector;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicalRecordService implements IService<MedicalRecord> {

    private final Connection connection;

    public MedicalRecordService() {
        this.connection = MySqlConnector.getConnection();
    }
    @Override
    public void add(MedicalRecord medicalRecord) {
        String sql = "INSERT INTO medical_record(exam_date, symptoms, diagnosis, note, id_appointment, id_room) VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, (java.sql.Date) medicalRecord.getExam_date());
            preparedStatement.setString(2, medicalRecord.getSymptoms());
            preparedStatement.setString(3, medicalRecord.getDiagnosis());
            preparedStatement.setString(4, medicalRecord.getNote());
            preparedStatement.setInt(5, medicalRecord.getAppointment().getId_appointment());
            preparedStatement.setInt(6, medicalRecord.getRoom().getId_room());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void edit(int id_medical_record, MedicalRecord medicalRecord) {
        String sql = "UPDATE medical_record SET exam_date = ?, symptoms = ?, diagnosis = ?, note = ?, id_appointment = ?, id_room = ? WHERE id_medical_record = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, (java.sql.Date) medicalRecord.getExam_date());
            preparedStatement.setString(2, medicalRecord.getSymptoms());
            preparedStatement.setString(3, medicalRecord.getDiagnosis());
            preparedStatement.setString(4, medicalRecord.getNote());
            preparedStatement.setInt(5, medicalRecord.getAppointment().getId_appointment());
            preparedStatement.setInt(6, medicalRecord.getRoom().getId_room());
            preparedStatement.setInt(7, id_medical_record);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void delete(int id_medical_record) {
        String sql = "DELETE FROM medical_record WHERE id_medical_record = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_medical_record);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public List<MedicalRecord> findAll() {
        String sql =  "SELECT m.id_medical_record, m.exam_date, m.symptoms, m.diagnosis, m.note,\n" +
                "                a.id_appointment, a.appointment_date AS appointment_date,\n" +
                "                r.id_room, r.room_name AS room_name\n" +
                "                FROM medical_record m\n" +
                "                LEFT JOIN appointment a ON m.id_appointment = a.id_appointment\n" +
                "                LEFT JOIN room r ON m.id_room = r.id_room\n" +
                "                ORDER BY m.id_medical_record;";
        List<MedicalRecord> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_medical_record = resultSet.getInt("id_medical_record");
                Date exam_date = resultSet.getDate("exam_date");
                String symptoms = resultSet.getString("symptoms");
                String diagnosis = resultSet.getString("diagnosis");
                String note = resultSet.getString("note");
                int id_appointment = resultSet.getInt("id_appointment");
                int id_room = resultSet.getInt("id_room");
                Date appointmentDate = resultSet.getDate("appointment_date");
                String roomName = resultSet.getString("room_name");
                Appointment appointment = new Appointment(id_appointment, appointmentDate);
                Room room = new Room(id_room, roomName);
                MedicalRecord medicalRecord = new MedicalRecord(id_medical_record, exam_date, symptoms, diagnosis, note, id_appointment, id_room, appointment, room);
                list.add(medicalRecord);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return list;
    }

    @Override
    public MedicalRecord findById(int id_medical_record) {
        String sql = "SELECT m.id_medical_record, m.exam_date, m.symptoms, m.diagnosis, m.note,\n" +
                "                a.id_appointment, a.appointment_date AS appointment_date,\n" +
                "                r.id_room, r.room_name AS room_name\n" +
                "                FROM medical_record m\n" +
                "                LEFT JOIN appointment a ON m.id_appointment = a.id_appointment\n" +
                "                LEFT JOIN room r ON m.id_room = r.id_room\n" +
                "                where m.id_medical_record = ?;";
        MedicalRecord medicalRecord = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_medical_record);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Date exam_date = resultSet.getDate("exam_date");
                    String symptoms = resultSet.getString("symptoms");
                    String diagnosis = resultSet.getString("diagnosis");
                    String note = resultSet.getString("note");
                    medicalRecord = new MedicalRecord(id_medical_record, exam_date, symptoms, diagnosis, note);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return medicalRecord;
    }
}
