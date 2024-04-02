package com.k1fl1k.dbpractice.persistance.DAO;

import com.k1fl1k.dbpractice.persistance.entity.Storage;
import com.k1fl1k.dbpractice.persistance.service.validation.Validation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StorageDAO {

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

    // Add a new storage to the database
    public void addStorage(Storage storage) {
        Validation storageValidation = new Validation()
            .name(storage.getName())
            .street(storage.getStreet())
            .sections(storage.getSections())
            .searchId(storage.getSearchId());

        List<String> validationErrors = storageValidation.validate();
        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException("Storage data is invalid: " + validationErrors);
        }

        String sql = "INSERT INTO Storage(id, name, street, sections, serchId) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(storage.getId()));
            pstmt.setString(2, storage.getName());
            pstmt.setString(3, storage.getStreet());
            pstmt.setInt(4, storage.getSections());
            pstmt.setInt(5, storage.getSearchId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // Retrieve a list of all storages from the database
    public List<Storage> getAllStorage() {
        String sql = "SELECT * FROM storage";
        List<Storage> storages = new ArrayList<>();

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            // Iterate through the query results and create storage objects
            while (rs.next()) {
                Storage storage = new Storage();
                storage.setId(UUID.fromString(rs.getString("id")));
                storage.setName(rs.getString("name"));
                storage.setStreet(rs.getString("streets"));
                storage.setSections(rs.getInt("sections"));
                storage.setSearchId(rs.getInt("serch_Id"));
                storages.add(storage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return storages;
    }

    // Retrieve an storage by its UUID from the database
    public Storage getStorageById(UUID id) {
        String sql = "SELECT * FROM storage WHERE id = ?";
        Storage storage = null;

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String street = rs.getString("street");
                int sections = rs.getInt("sections");
                int serchId = rs.getInt("serch_Id");

                storage = new Storage(id, name, street, sections, serchId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return storage;
    }

    // Update an existing storage in the database
    public void updateStorage(Storage storage) {
        String sql = "UPDATE storage SET name = ?, street = ?, sections = ?, search_id = ? WHERE id = ?";

        try (Connection conn = this.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, storage.getName());
                    pstmt.setString(2, storage.getStreet());
                    pstmt.setInt(3, storage.getSections());
                    pstmt.setInt(4, storage.getSearchId());
                    pstmt.setString(5, storage.getId().toString());
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Connection to the database is null.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    // Delete an storage from the database by its UUID
    public void deleteStorage(UUID id) {
        String sql = "DELETE FROM storage WHERE id = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

