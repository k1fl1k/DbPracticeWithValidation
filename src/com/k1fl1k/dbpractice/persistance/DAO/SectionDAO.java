package com.k1fl1k.dbpractice.persistance.DAO;

import com.k1fl1k.dbpractice.persistance.entity.Sections;
import com.k1fl1k.dbpractice.persistance.service.validation.Validation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SectionDAO {
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

    // Add a new section to the database
    public void addSection(Sections sections) {
        Validation sectionValidation = new Validation()
            .name(sections.getName())
            .value(sections.getValue())
            .inside(sections.getInside())
            .searchId(sections.getSearchId());

        List<String> validationErrors = sectionValidation.validate();
        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException("Section data is invalid: " + validationErrors);
        }

        String sql = "INSERT INTO section(id, name, inside, value, serchId) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(sections.getId()));
            pstmt.setString(2, sections.getName());
            pstmt.setString(3, sections.getInside());
            pstmt.setInt(4, sections.getValue());
            pstmt.setInt(5, sections.getSearchId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // Retrieve a list of all sections from the database
    public List<Sections> getAllSections() {
        String sql = "SELECT * FROM section";
        List<Sections> section = new ArrayList<>();

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            // Iterate through the query results and create storage objects
            while (rs.next()) {
                Sections sections = new Sections();
                sections.setId(UUID.fromString(rs.getString("id")));
                sections.setName(rs.getString("name"));
                sections.setInside(rs.getString("inside"));
                sections.setValue(rs.getInt("value"));
                sections.setSearchId(rs.getInt("serch_Id"));
                section.add(sections);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return section;
    }

    // Retrieve a section by its UUID from the database
    public Sections getSectionById(UUID id) {
        String sql = "SELECT * FROM section WHERE id = ?";
        Sections sections = null;

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String inside = rs.getString("inside");
                int value = rs.getInt("value");
                int serchId = rs.getInt("serch_Id");

                sections = new Sections(id, name, inside, value, serchId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sections;
    }

    // Update an existing section in the database
    public void updateSection(Sections sections) {
        String sql = "UPDATE section SET name = ?, inside = ?, value = ?, serch_id = ? WHERE id = ?";

        try (Connection conn = this.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, sections.getName());
                    pstmt.setString(2, sections.getInside());
                    pstmt.setInt(3, sections.getValue());
                    pstmt.setInt(4, sections.getSearchId());
                    pstmt.setString(5, sections.getId().toString());
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

    // Delete a section from the database by its UUID
    public void deleteSection(UUID id) {
        String sql = "DELETE FROM section WHERE id = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
