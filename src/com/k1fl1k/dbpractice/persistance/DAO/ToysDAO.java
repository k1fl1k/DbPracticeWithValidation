package com.k1fl1k.dbpractice.persistance.DAO;

import com.k1fl1k.dbpractice.persistance.entity.Toys;
import com.k1fl1k.dbpractice.persistance.service.validation.Validation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class ToysDAO {

    private Connection connect(){
        String url = "jdbc:h2:src/com/k1fl1k/dbpractice/persistance/database/storages_database";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addToy(Toys toy) {
        Validation toysValidation = new Validation()
            .type(toy.getType())
            .value(toy.getValue())
            .price(toy.getPrice())
            .searchId(toy.getSearchId());

        List<String> validationErrors = toysValidation.validate();
        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException("Toy data is invalid: " + validationErrors);
        }

        String sql = "INSERT INTO Toys(id, type, value, price, search_id) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(toy.getId()));
            pstmt.setString(2, toy.getType());
            pstmt.setInt(3, toy.getValue());
            pstmt.setInt(4, toy.getPrice());
            pstmt.setInt(5, toy.getSearchId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add toy to the database: " + e.getMessage(), e);
        }
    }

    public List<Toys> getAllToys() {
        String sql = "SELECT * FROM Toys";
        List<Toys> toysList = new ArrayList<>();

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Toys toy = new Toys();
                toy.setId(UUID.fromString(rs.getString("id")));
                toy.setType(rs.getString("type"));
                toy.setValue(rs.getInt("value"));
                toy.setPrice(rs.getInt("price"));
                toy.setSearchId(rs.getInt("search_id"));
                toysList.add(toy);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve toys from the database: " + e.getMessage(), e);
        }
        return toysList;
    }

    public Toys getToyById(UUID id) {
        String sql = "SELECT * FROM Toys WHERE id = ?";
        Toys toy = null;

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");
                int value = rs.getInt("value");
                int price = rs.getInt("price");
                int searchId = rs.getInt("search_id");

                toy = new Toys(id, type, value, price, searchId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve toy by ID from the database: " + e.getMessage(), e);
        }
        return toy;
    }

    public void updateToy(Toys toy) {
        String sql = "UPDATE Toys SET type = ?, value = ?, price = ?, search_id = ? WHERE id = ?";

        try (Connection conn = this.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, toy.getType());
                    pstmt.setInt(2, toy.getValue());
                    pstmt.setInt(3, toy.getPrice());
                    pstmt.setInt(4, toy.getSearchId());
                    pstmt.setString(5, toy.getId().toString());
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Failed to update toy in the database: " + e.getMessage(), e);
                }
            } else {
                System.out.println("Connection to the database is null.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage(), e);
        }
    }

    public void deleteToy(UUID id) {
        String sql = "DELETE FROM Toys WHERE id = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete toy from the database: " + e.getMessage(), e);
        }
    }
}
