package entity;

import java.util.Date;

public class Admin {
    private int id_admin;
    private String username;
    private String password_hash;
    private String salt;
    private String full_name;
    private String email;
    private Date created_at;

    public Admin() {}

    // Constructor cho đăng ký (chưa có id)
    public Admin(String username, String password_hash, String salt, String full_name, String email) {
        this.username = username;
        this.password_hash = password_hash;
        this.salt = salt;
        this.full_name = full_name;
        this.email = email;
    }

    // Constructor đầy đủ
    public Admin(int id_admin, String username, String password_hash, String salt, String full_name, String email) {
        this.id_admin = id_admin;
        this.username = username;
        this.password_hash = password_hash;
        this.salt = salt;
        this.full_name = full_name;
        this.email = email;
    }

    // getters / setters
    public int getId_admin() { return id_admin; }
    public void setId_admin(int id_admin) { this.id_admin = id_admin; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword_hash() { return password_hash; }
    public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }
    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }
    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getCreated_at() { return created_at; }
    public void setCreated_at(Date created_at) { this.created_at = created_at; }
}
