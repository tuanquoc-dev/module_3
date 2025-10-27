package service;
import configuration.MySqlConnector;
import entity.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorService implements IService<Doctor> {
    private final Connection connection;

    public DoctorService() {
        connection = MySqlConnector.getConnection();
    }


    @Override
    public void add(Doctor doctor) {
        String sql = "insert into doctor(name, specialty, qualification, phone) values(?,?,?,?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSpecialty());
            preparedStatement.setString(3, doctor.getQualification());
            preparedStatement.setString(4, doctor.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void edit(int id_doctor, Doctor doctor) {
        String sql = "update doctor set name = ?, specialty = ?, qualification = ?, phone = ? where id_doctor = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSpecialty());
            preparedStatement.setString(3, doctor.getQualification());
            preparedStatement.setString(4, doctor.getPhone());
            preparedStatement.setInt(5, id_doctor);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void delete(int id_doctor) {
        String sql = "delete from doctor where id_doctor = ?;";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_doctor);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public List<Doctor> findAll() {
        String sql = "SELECT * FROM doctor;";
        List<Doctor> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_doctor = resultSet.getInt("id_doctor");
                String name = resultSet.getString("name");
                String specialty = resultSet.getString("specialty");
                String qualification = resultSet.getString("qualification");
                String phone = resultSet.getString("phone");
                Doctor doctor = new Doctor(id_doctor, name, specialty, qualification, phone);
                list.add(doctor);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return list;
    }


    @Override
    public Doctor findById(int id_doctor) {
        String sql = "select * from doctor where id_doctor = ?;";
        Doctor doctor = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_doctor);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            String specialty = resultSet.getString("specialty");
            String qualification = resultSet.getString("qualification");
            String phone = resultSet.getString("phone");
            doctor = new Doctor(id_doctor, name, specialty, qualification, phone);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return doctor;
    }
}
