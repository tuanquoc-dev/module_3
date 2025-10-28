package entity;

public class Room {
    private int id_room;
    private String room_name;
    private String department;
    private String equipment;

    public Room(int id_room, String room_name, String department, String equipment) {
        this.id_room = id_room;
        this.room_name = room_name;
        this.department = department;
        this.equipment = equipment;
    }

    public Room(String room_name, String department, String equipment) {
        this.room_name = room_name;
        this.department = department;
        this.equipment = equipment;
    }

    public Room(int id_room, String room_name) {
        this.id_room = id_room;
        this.room_name = room_name;
    }

    public Room(int id_room) {
        this.id_room = id_room;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
