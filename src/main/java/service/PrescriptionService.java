package service;

import configuration.MySqlConnector;
import entity.MedicalRecord;
import entity.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrescriptionService implements IService<Prescription> {

    private final Connection connection;

    public PrescriptionService() {
        this.connection = MySqlConnector.getConnection();
    }
    @Override
    public void add(Prescription prescription) {
        String sql = "INSERT INTO prescription(prescription_date, note, id_medical_record) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(prescription.getPrescription_date().getTime()));
            preparedStatement.setString(2, prescription.getNote());
            preparedStatement.setInt(3,prescription.getId_medical_record());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void edit(int id_prescription, Prescription prescription) {
        String sql = "UPDATE prescription SET prescription_date = ?, note = ?, id_medical_record = ? WHERE id_prescription = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(prescription.getPrescription_date().getTime()));
            preparedStatement.setString(2, prescription.getNote());
            preparedStatement.setInt(3, prescription.getId_medical_record());
            preparedStatement.setInt(4, id_prescription);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void delete(int id_prescription) {
        String sql = "DELETE FROM prescription WHERE id_prescription = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_prescription);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public List<Prescription> findAll() {
        String sql =  "SELECT pr.id_prescription, pr.prescription_date, pr.note,\n" +
                "       m.id_medical_record, m.exam_date\n" +
                "FROM prescription pr\n" +
                "JOIN medical_record m ON pr.id_medical_record = m.id_medical_record\n" +
                "ORDER BY pr.id_prescription;\n";
        List<Prescription> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_prescription = resultSet.getInt("id_prescription");
                Date prescription_date = resultSet.getDate("prescription_date");
                String note = resultSet.getString("note");
                int id_medical_record = resultSet.getInt("id_medical_record");
                Date exam_date = resultSet.getDate("exam_date");
                MedicalRecord medicalRecord = new MedicalRecord(id_medical_record, exam_date);
                Prescription prescription = new Prescription(id_prescription, prescription_date, note, id_medical_record, medicalRecord);
                list.add(prescription);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return list;
    }

    @Override
    public Prescription findById(int id_prescription) {
        String sql = "SELECT pr.id_prescription, pr.prescription_date, pr.note,\n" +
                "                                m.id_medical_record, m.exam_date AS exam_date\n" +
                "                                FROM prescription pr\n" +
                "                                LEFT JOIN medical_record m ON pr.id_medical_record = m.id_medical_record\n" +
                "                where pr.id_prescription = ?;";
        Prescription prescription = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_prescription);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Date prescription_date = resultSet.getDate("prescription_date");
                    String note = resultSet.getString("note");
                    prescription = new Prescription(id_prescription, prescription_date,note);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return prescription;
    }
}
