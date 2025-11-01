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

public class MedicineService implements IService<Medicine> {

    private final Connection connection;

    public MedicineService() {
        this.connection = MySqlConnector.getConnection();
    }

    @Override
    public void add(Medicine medicine) {
        String sql = "INSERT INTO medicine(medicine_name, default_dosage, frequency, days, id_prescription) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, medicine.getMedicine_name());
            preparedStatement.setString(2, medicine.getDefault_dosage());
            preparedStatement.setString(3, medicine.getFrequency());
            preparedStatement.setInt(4, medicine.getDays());
            preparedStatement.setInt(5, medicine.getPrescription().getId_prescription());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void edit(int id, Medicine medicine) {
        String sql = "UPDATE medicine SET medicine_name = ?, default_dosage = ?, frequency = ?, days = ?, id_prescription = ? WHERE id_medicine = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, medicine.getMedicine_name());
            preparedStatement.setString(2, medicine.getDefault_dosage());
            preparedStatement.setString(3, medicine.getFrequency());
            preparedStatement.setInt(4, medicine.getDays());
            preparedStatement.setInt(5, medicine.getPrescription().getId_prescription());
            preparedStatement.setInt(6, id); // dùng id truyền vào
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void delete(int id_medicine) {
        String sql = "DELETE FROM medicine WHERE id_medicine = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_medicine);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public List<Medicine> findAll() {
        String sql =  "SELECT m.id_medicine, m.medicine_name, m.default_dosage, m.frequency, m.days," +
                " p.id_prescription, p.prescription_date AS prescription_date " +
                "FROM medicine m " +
                "LEFT JOIN prescription p ON m.id_prescription = p.id_prescription " +
                "ORDER BY m.id_medicine;";
        List<Medicine> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_medicine = resultSet.getInt("id_medicine");
                String medicine_name = resultSet.getString("medicine_name");
                String default_dosage = resultSet.getString("default_dosage");
                String frequency = resultSet.getString("frequency");
                int days =  resultSet.getInt("days");
                int id_prescription = resultSet.getInt("id_prescription");
                Date prescription_date = resultSet.getDate("prescription_date");
                Prescription prescription = new Prescription(id_prescription, prescription_date);
                Medicine medicine = new Medicine(id_medicine, medicine_name, default_dosage, frequency, days, id_prescription, prescription);
                list.add(medicine);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return list;
    }

    @Override
    public Medicine findById(int id_medicine) {
        String sql = "SELECT m.id_medicine, m.medicine_name, m.default_dosage, m.frequency, m.days," +
                " p.id_prescription, p.prescription_date AS prescription_date " +
                "FROM medicine m " +
                "LEFT JOIN prescription p ON m.id_prescription = p.id_prescription " +
                "WHERE m.id_medicine = ?;";
        Medicine medicine = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_medicine);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String medicine_name = resultSet.getString("medicine_name");
                    String default_dosage = resultSet.getString("default_dosage");
                    String frequency = resultSet.getString("frequency");
                    int days =  resultSet.getInt("days");
                    int id_prescription = resultSet.getInt("id_prescription");
                    Date prescription_date = resultSet.getDate("prescription_date");
                    Prescription prescription = new Prescription(id_prescription, prescription_date);
                    medicine = new Medicine(id_medicine, medicine_name, default_dosage, frequency, days, id_prescription, prescription);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return medicine;
    }

    public List<Medicine> findByName(String keyword) {
        String sql =  "SELECT m.id_medicine, m.medicine_name, m.default_dosage, m.frequency, m.days, " +
                " p.id_prescription, p.prescription_date AS prescription_date " +
                "FROM medicine m " +
                "LEFT JOIN prescription p ON m.id_prescription = p.id_prescription " +
                "WHERE LOWER(m.medicine_name) LIKE ? " +
                "ORDER BY m.id_medicine;";
        List<Medicine> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + keyword.toLowerCase() + "%"); // tìm kiếm gần đúng
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id_medicine = resultSet.getInt("id_medicine");
                    String medicine_name = resultSet.getString("medicine_name");
                    String default_dosage = resultSet.getString("default_dosage");
                    String frequency = resultSet.getString("frequency");
                    int days = resultSet.getInt("days");
                    int id_prescription = resultSet.getInt("id_prescription");
                    Date prescription_date = resultSet.getDate("prescription_date");

                    Prescription prescription = new Prescription(id_prescription, prescription_date);
                    Medicine medicine = new Medicine(id_medicine, medicine_name, default_dosage, frequency, days, id_prescription, prescription);
                    list.add(medicine);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return list;
    }

}
