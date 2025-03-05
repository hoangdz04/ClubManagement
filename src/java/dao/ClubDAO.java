/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Clubs;
import model.Users;
import java.sql.Statement;

/**
 *
 * @author A
 */
public  class ClubDAO {
    private Connection conn;
    

    public ClubDAO() {
        conn = DBContext.getInstance().getConnection();
    }

    public List<Clubs> getAllClubs() {
        List<Clubs> clubs = new ArrayList<>();
        String query = "SELECT * FROM Clubs";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Clubs club = new Clubs(
                    rs.getInt("clubId"),
                    rs.getString("clubName"),
                    rs.getString("description"),
                    rs.getDate("establishedDate")
                );
                clubs.add(club);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
    }
    
    public Users getUserByEmail(String email) {
        String query = "SELECT * FROM Users WHERE email = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Clubs getClubById(int clubId) {
        String query = "SELECT * FROM Clubs WHERE clubId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, clubId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Clubs(
                        rs.getInt("clubId"),
                        rs.getString("clubName"),
                        rs.getString("description"),
                        rs.getDate("establishedDate")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addClub(Clubs club) {
        String query = "INSERT INTO Clubs (clubName, description, establishedDate) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, club.getClubName());
            ps.setString(2, club.getDescription());
            ps.setDate(3, new java.sql.Date(club.getEstablishedDate().getTime()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateClub(Clubs club) {
        String query = "UPDATE Clubs SET clubName = ?, description = ?, establishedDate = ? WHERE clubId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, club.getClubName());
            ps.setString(2, club.getDescription());
            ps.setDate(3, new java.sql.Date(club.getEstablishedDate().getTime()));
            ps.setInt(4, club.getClubId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteClub(int clubId) {
        String query = "DELETE FROM Clubs WHERE clubId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, clubId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
//    public boolean deleteClub(int clubId) {
//    String deleteQuery = "DELETE FROM Clubs WHERE clubId = ?";
//    String renumberQuery = "WITH Renumbered AS ( " +
//                           "    SELECT ClubId, ROW_NUMBER() OVER (ORDER BY ClubId) AS NewId " +
//                           "    FROM Clubs " +
//                           ") " +
//                           "UPDATE C " +
//                           "SET C.ClubId = R.NewId " +
//                           "FROM Clubs C " +
//                           "JOIN Renumbered R ON C.ClubId = R.ClubId;";
//    String resetIdentityQuery = "DBCC CHECKIDENT ('Clubs', RESEED, 0);";
//
//    try (PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
//        ps.setInt(1, clubId);
//        int rowsAffected = ps.executeUpdate();
//        
//        if (rowsAffected > 0) {
//            try (Statement stmt = conn.createStatement()) {
//                stmt.executeUpdate(renumberQuery);  // Cập nhật lại ID liên tục
//                stmt.executeUpdate(resetIdentityQuery);  // Reset Identity về 0
//            }
//        }
//        return rowsAffected > 0;
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return false;
//}


}
