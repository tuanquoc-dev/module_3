package service;

import configuration.MySqlConnector;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomService implements IService<Room> {

    private final Connection connection;

    public RoomService() {
        this.connection = MySqlConnector.getConnection();
    }

    @Override
    public void add(Room room) {
        String sql = "insert into room(room_name, department, equipment) values(?,?,?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, room.getRoom_name());
            preparedStatement.setString(2, room.getDepartment());
            preparedStatement.setString(3, room.getEquipment());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void edit(int id_room, Room room) {
        String sql = "update room set room_name = ?, department = ?, equipment = ? where id_room = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, room.getRoom_name());
            preparedStatement.setString(2, room.getDepartment());
            preparedStatement.setString(3, room.getEquipment());
            preparedStatement.setInt(4, id_room);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void delete(int id_room) {
        String sql = "delete from room where id_room = ?;";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_room);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public List<Room> findAll() {
        String sql = "SELECT * FROM room;";
        List<Room> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_room = resultSet.getInt("id_room");
                String room_name = resultSet.getString("room_name");
                String department = resultSet.getString("department");
                String equipment = resultSet.getString("equipment");
                Room room = new Room(id_room, room_name, department, equipment);
                list.add(room);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return list;
    }

    @Override
    public Room findById(int id_room) {
        String sql = "select * from room where id_room = ?;";
        Room room = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_room);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String room_name = resultSet.getString("room_name");
            String department = resultSet.getString("department");
            String equipment = resultSet.getString("equipment");
            room = new Room(id_room, room_name, department, equipment);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return room;
    }
}
