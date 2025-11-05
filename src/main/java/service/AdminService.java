package service;

import configuration.MySqlConnector;
import entity.Admin;
import util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private final Connection connection;

    public AdminService() {
        this.connection = MySqlConnector.getConnection();
    }

    // Thêm admin (dùng khi register)
    public boolean add(Admin admin) {
        String sql = "INSERT INTO admin(username, password_hash, salt, full_name, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword_hash());
            ps.setString(3, admin.getSalt());
            ps.setString(4, admin.getFull_name());
            ps.setString(5, admin.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("AdminService.add: " + ex.getMessage());
            return false;
        }
    }

    // Tìm admin theo username
    public Admin findByUsername(String username) {
        String sql = "SELECT id_admin, username, password_hash, salt, full_name, email, created_at FROM admin WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Admin a = new Admin();
                    a.setId_admin(rs.getInt("id_admin"));
                    a.setUsername(rs.getString("username"));
                    a.setPassword_hash(rs.getString("password_hash"));
                    a.setSalt(rs.getString("salt"));
                    a.setFull_name(rs.getString("full_name"));
                    a.setEmail(rs.getString("email"));
                    a.setCreated_at(rs.getTimestamp("created_at"));
                    return a;
                }
            }
        } catch (SQLException ex) {
            System.out.println("AdminService.findByUsername: " + ex.getMessage());
        }
        return null;
    }

    // Xác thực username + password (trả về Admin nếu ok)
    public Admin authenticate(String username, String password) {
        Admin a = findByUsername(username);
        if (a == null) return null;
        String computedHash = PasswordUtil.hashPassword(password, a.getSalt());
        if (computedHash.equalsIgnoreCase(a.getPassword_hash())) {
            return a;
        }
        return null;
    }

    // (tùy chọn) danh sách admin
    public List<Admin> findAll() {
        List<Admin> list = new ArrayList<>();
        String sql = "SELECT id_admin, username, full_name, email, created_at FROM admin ORDER BY id_admin";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Admin a = new Admin();
                a.setId_admin(rs.getInt("id_admin"));
                a.setUsername(rs.getString("username"));
                a.setFull_name(rs.getString("full_name"));
                a.setEmail(rs.getString("email"));
                a.setCreated_at(rs.getTimestamp("created_at"));
                list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("AdminService.findAll: " + ex.getMessage());
        }
        return list;
    }
}
