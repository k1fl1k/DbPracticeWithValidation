package com.k1fl1k.dbpractice.persistance.DAO;

import com.k1fl1k.dbpractice.persistance.entity.Users;

import com.k1fl1k.dbpractice.persistance.service.validation.Validation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsersDAO {

    private Connection connect() {
        String url = "jdbc:h2:src/com/k1fl1k/dbpractice/persistance/database/storages_database";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage(), e);
        }
        return conn;
    }

    public void addUser(Users user) {
        Validation userValidation = new Validation()
            .name(user.getName())
            .surname(user.getSurname())
            .post(user.getPost())
            .searchId(user.getSearchId());

        List<String> validationErrors = userValidation.validate();
        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException("Section data is invalid: " + validationErrors);
        }

        String sql = "INSERT INTO Users(id, name, surname, post, search_id) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(user.getId()));
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getSurname());
            pstmt.setString(4, user.getPost());
            pstmt.setInt(5, user.getSearchId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add user to the database: " + e.getMessage(), e);
        }
    }

    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM Users";
        List<Users> usersList = new ArrayList<>();

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Users user = new Users();
                user.setId(UUID.fromString(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setPost(rs.getString("post"));
                user.setSearchId(rs.getInt("search_id"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve users from the database: " + e.getMessage(), e);
        }
        return usersList;
    }

    public Users getUserById(UUID id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        Users user = null;

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String post = rs.getString("post");
                int searchId = rs.getInt("search_id");

                user = new Users(id, name, surname, post, searchId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve user by ID from the database: " + e.getMessage(), e);
        }
        return user;
    }

    public void updateUser(Users user) {
        String sql = "UPDATE Users SET name = ?, surname = ?, post = ?, search_id = ? WHERE id = ?";

        try (Connection conn = this.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, user.getName());
                    pstmt.setString(2, user.getSurname());
                    pstmt.setString(3, user.getPost());
                    pstmt.setInt(4, user.getSearchId());
                    pstmt.setString(5, user.getId().toString());
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Failed to update user in the database: " + e.getMessage(), e);
                }
            } else {
                System.out.println("Connection to the database is null.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage(), e);
        }
    }

    public void deleteUser(UUID id) {
        String sql = "DELETE FROM Users WHERE id = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user from the database: " + e.getMessage(), e);
        }
    }
}
