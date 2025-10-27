package service;

import configuration.MySqlConnector;
import entity.Appointment;
import entity.Doctor;
import entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentService implements IService<Appointment> {

    private final Connection connection;

    public AppointmentService() {
        connection = MySqlConnector.getConnection();
    }

    @Override
    public void add(Appointment appointment) {
        String sql = "INSERT INTO appointment(appointment_date, status, id_patient, id_doctor) VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, (java.sql.Date) appointment.getAppointment_date());
            preparedStatement.setString(2, appointment.getStatus());
            preparedStatement.setInt(3, appointment.getPatient().getId_patient());
            preparedStatement.setInt(4, appointment.getDoctor().getId_doctor());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void edit(int id_appointment, Appointment appointment) {
        String sql = "UPDATE appointment SET appointment_date = ?, status = ?, id_patient = ?, id_doctor = ? WHERE id_appointment = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, (java.sql.Date) appointment.getAppointment_date());
            preparedStatement.setString(2, appointment.getStatus());
            preparedStatement.setInt(3, appointment.getPatient().getId_patient());
            preparedStatement.setInt(4, appointment.getDoctor().getId_doctor());
            preparedStatement.setInt(5, id_appointment);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void delete(int id_appointment) {
        String sql = "DELETE FROM appointment WHERE id_appointment = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_appointment);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public List<Appointment> findAll() {
        String sql =  "SELECT a.id_appointment, a.appointment_date, a.status, " +
                "p.id_patient, p.name AS patient_name, " +
                "d.id_doctor, d.name AS doctor_name " +
                "FROM appointment a " +
                "LEFT JOIN patient p ON a.id_patient = p.id_patient " +
                "LEFT JOIN doctor d ON a.id_doctor = d.id_doctor " +
                "ORDER BY a.id_appointment;";
        List<Appointment> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id_appointment = resultSet.getInt("id_appointment");
                Date  appointment_date = resultSet.getDate("appointment_date");
                String status = resultSet.getString("status");
                int id_patient = resultSet.getInt("id_patient");
                int id_doctor = resultSet.getInt("id_doctor");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                Patient patient = new Patient(id_patient, patientName);
                Doctor doctor = new Doctor(id_doctor, doctorName);
                Appointment appointment = new Appointment(id_appointment, appointment_date, status, patient, doctor);
                list.add(appointment);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return list;
    }

    @Override
    public Appointment findById(int id_appointment) {
        String sql =  "SELECT a.id_appointment, a.appointment_date, a.status, " +
                "p.id_patient, p.name AS patient_name, " +
                "d.id_doctor, d.name AS doctor_name " +
                "FROM appointment a " +
                "LEFT JOIN patient p ON a.id_patient = p.id_patient " +
                "LEFT JOIN doctor d ON a.id_doctor = d.id_doctor " +
                "WHERE a.id_appointment = ?;";
        Appointment appointment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_appointment);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Date  appointment_date = resultSet.getDate("appointment_date");
                    String status = resultSet.getString("status");
                    appointment = new Appointment(id_appointment, appointment_date, status);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return appointment;
    }
}
