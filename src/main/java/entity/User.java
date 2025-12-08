package entity;

import java.util.Date;

public class User {
    private int id_user;
    private String username;
    private String password_hash;
    private String salt;
    private String full_name;
    private String email;
    private Date created_at;

    public User() {}

    public User(String username, String password_hash, String salt, String full_name, String email) {
        this.username = username;
        this.password_hash = password_hash;
        this.salt = salt;
        this.full_name = full_name;
        this.email = email;
    }

    public int getId_user() { return id_user; }
    public void setId_user(int id_user) { this.id_user = id_user; }
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
