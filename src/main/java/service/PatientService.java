package service;

import configuration.MySqlConnector;
import entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientService implements IService<Patient>{

    private final Connection connection;

    public PatientService() {
        connection = MySqlConnector.getConnection();
    }

    @Override
    public void add(Patient patient) {
        String sql = "insert into patient(name, birth_date, gender, phone, address) values(?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getBirth_date().toString());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getPhone());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void edit(int id_patient, Patient patient) {
        String sql = "update patient set name = ?, birth_date = ?, gender = ?, phone = ?, address = ? where id_patient = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getBirth_date().toString());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getPhone());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setInt(6, id_patient);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void delete(int id_patient) {
        String sql = "delete from patient where id_patient = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_patient);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public List<Patient> findAll() {
        String sql = "SELECT * FROM patient;";
        List<Patient> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_patient = resultSet.getInt("id_patient");
                String name = resultSet.getString("name");
                Date birth_date = resultSet.getDate("birth_date");
                String gender = resultSet.getString("gender");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Patient patient = new Patient(id_patient, name, birth_date, gender, phone, address);
                list.add(patient);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return list;
    }

    @Override
    public Patient findById(int id_patient) {
        String sql = "select * from patient where id_patient = ?;";
        Patient patient = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_patient);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            Date birth_date = resultSet.getDate("birth_date");
            String gender = resultSet.getString("gender");
            String phone = resultSet.getString("phone");
            String address = resultSet.getString("address");
            patient = new Patient(id_patient, name, birth_date, gender, phone, address);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return patient;
    }
}
