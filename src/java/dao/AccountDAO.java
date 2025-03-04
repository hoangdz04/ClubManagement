/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Role;
import model.Users;
/**
 *
 * @author A
 */
public class AccountDAO {
    

public static Users getAccountsByUserAndPassword(String user, String password){
        DBContext db = DBContext.getInstance();
        ArrayList<Users> accounts = new ArrayList<Users>();
        try {
            String sql = """
                            select * 
                            from Users
                            where email = ? and password = ?
                         """;
            PreparedStatement statment = db.getConnection().prepareStatement(sql);
            statment.setString(1, user);
            statment.setString(2, password);
            ResultSet rs = statment.executeQuery();
            while(rs.next()){
      
                Users account = new Users(
                        rs.getInt("userId"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getInt("clubId")
                );
                accounts.add(account);
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        if(accounts.isEmpty()) return null; 
        else return accounts.get(0);
    }
    
    public static ArrayList<Users> getAccounts(){
        DBContext db = DBContext.getInstance();
        ArrayList<Users> accounts = new ArrayList<Users>();
        try{
            String sql = "select * from Accounts";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Users account = new Users(
                        rs.getInt("userId"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getInt("clubId")
                );
                accounts.add(account);
            }
        }catch(Exception e){
            return null;
        }
        if(accounts.isEmpty()) return null;
        else return accounts;
    }
    public static Users addAccount(Users account){
    DBContext db = DBContext.getInstance();
    int rs = 0;
    try {
        String sql = """
                     INSERT INTO Users (fullName, email, password, roleId) 
                     VALUES (?, ?, ?, 5)
                     """;
        PreparedStatement statement = db.getConnection().prepareStatement(sql);
        statement.setString(1, account.getFullName());
        statement.setString(2, account.getEmail());
        statement.setString(3, account.getPassword());

        rs = statement.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    if (rs == 0) return null;
    else {
        // Gán lại roleId cho đối tượng trước khi trả về
        account.setRoleId(1);
        return account;
    }
}
}
