/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Users;
/**
 *
 * @author A
 */
public class UsersDAO {
    private Connection connection;

    public UsersDAO() {
        this.connection = DBContext.getInstance().getConnection();
    }

    // Lấy danh sách tất cả người dùng
    public List<Users> getAllUsers() throws SQLException {
        List<Users> usersList = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Users user = new Users(
                        rs.getInt("userId"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getInt("clubId")
                );
                usersList.add(user);
            }
        }
        return usersList;
    }
    
    //Lấy ra người dùng có clubId = 1 số cho trước
    public List<Users> getUsersByClubId(int clubId) throws SQLException {
    List<Users> usersList = new ArrayList<>();
    String query = "SELECT * FROM Users WHERE clubId = ?";
    
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, clubId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Users user = new Users(
                        rs.getInt("userId"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getInt("clubId")
                );
                usersList.add(user);
            }
        }
    }
    return usersList;
}

    // Thêm người dùng mới
    public void addUser(Users user) throws SQLException {
        String query = "INSERT INTO Users (fullName, email, password, roleId, clubId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRoleId());
            ps.setInt(5, user.getClubId());
            ps.executeUpdate();
        }
    }

    // Cập nhật thông tin người dùng
    public void updateUser(Users user) throws SQLException {
        String query = "UPDATE Users SET fullName=?, email=?, password=?, roleId=?, clubId=? WHERE userId=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRoleId());
            ps.setInt(5, user.getClubId());
            ps.setInt(6, user.getUserId());
            ps.executeUpdate();
        }
    }

    // Xóa người dùng theo ID
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM Users WHERE userId=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }

    // Tìm người dùng theo ID
    public Users getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM Users WHERE userId=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Users(
                            rs.getInt("userId"),
                            rs.getString("fullName"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("roleId"),
                            rs.getInt("clubId")
                    );
                }
            }
        }
        return null;
    }
}
