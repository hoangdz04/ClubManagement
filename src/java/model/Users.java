/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author A
 */
public class Users {
    private int userId;
    private String fullName;
    private String email;
    private String password;
    private int roleId;
    private int clubId;
    private String clubName;

    public Users() {
    }
    
    public Users(String fullName, String email, String password, int roleId) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public Users(int userId, String fullName, String email, String password, int roleId, int clubId) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.clubId = clubId;
    }
    
    public Users(int userId, String fullName, String email, String password, int roleId, int clubId, String clubName) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.clubId = clubId;
        this.clubName = clubName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", roleId=" + roleId + ", clubId=" + clubId + ", clubName=" + clubName + '}';
    }
    
    public boolean isAdmin() {
        return this.roleId == 1;
    }

    public boolean isChairman() {
        return this.roleId == 2;
    }

    public boolean isTeamLeader() {
        return this.roleId == 3;
    }

    public boolean isMember() {
        return this.roleId == 4;
    }

    
}
