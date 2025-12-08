package service;

import configuration.MySqlConnector;
import entity.User;
import util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final Connection connection;

    public UserService() {
        this.connection = MySqlConnector.getConnection();
    }

    // ====================== THÊM USER ======================
    public boolean add(User user) {
        String sql = "INSERT INTO user (username, password_hash, salt, full_name, email, created_at) VALUES (?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword_hash());
            ps.setString(3, user.getSalt());
            ps.setString(4, user.getFull_name());
            ps.setString(5, user.getEmail());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ UserService.add: Thêm người dùng thành công (" + user.getUsername() + ")");
                return true;
            } else {
                System.out.println("⚠️ UserService.add: Không thêm được user (executeUpdate trả về 0)");
                return false;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("❌ Lỗi: Username hoặc email đã tồn tại (" + e.getMessage() + ")");
            return false;
        } catch (SQLException ex) {
            System.out.println("❌ UserService.add SQL Error: " + ex.getMessage());
            return false;
        }
    }

    // ====================== TÌM USER THEO USERNAME ======================
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId_user(rs.getInt("id_user"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword_hash(rs.getString("password_hash"));
                    u.setSalt(rs.getString("salt"));
                    u.setFull_name(rs.getString("full_name"));
                    u.setEmail(rs.getString("email"));
                    u.setCreated_at(rs.getTimestamp("created_at"));
                    return u;
                }
            }
        } catch (SQLException ex) {
            System.out.println("❌ UserService.findByUsername: " + ex.getMessage());
        }
        return null;
    }

    // ====================== XÁC THỰC USER ======================
    public User authenticate(String username, String password) {
        User u = findByUsername(username);
        if (u == null) {
            System.out.println("⚠️ Không tìm thấy user: " + username);
            return null;
        }

        String computed = PasswordUtil.hashPassword(password, u.getSalt());
        if (computed.equalsIgnoreCase(u.getPassword_hash())) {
            System.out.println("✅ Đăng nhập thành công cho user: " + username);
            return u;
        } else {
            System.out.println("❌ Sai mật khẩu cho user: " + username);
            return null;
        }
    }

    // ====================== LẤY TẤT CẢ USER ======================
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT id_user, username, full_name, email, created_at FROM user ORDER BY created_at DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User u = new User();
                u.setId_user(rs.getInt("id_user"));
                u.setUsername(rs.getString("username"));
                u.setFull_name(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setCreated_at(rs.getTimestamp("created_at"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("❌ UserService.findAll: " + ex.getMessage());
        }
        return list;
    }
}
