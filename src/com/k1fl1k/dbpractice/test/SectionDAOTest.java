package com.k1fl1k.dbpractice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.k1fl1k.dbpractice.persistance.DAO.SectionDAO;
import com.k1fl1k.dbpractice.persistance.entity.Sections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SectionDAOTest {


    private SectionDAO sectionDAO;

    @BeforeEach
    public void setUp() {
        // Initialize the sectionDAO object before each test
        sectionDAO = new SectionDAO();
    }

    @Test
    public void testAddSection() {
        // Create a test section
        UUID id = UUID.randomUUID();
        String name = "Section 1";
        String inside = "Smart toy";
        int value = 5;
        int serchId = 1;
        Sections testSection = new Sections(id, name, inside, value, serchId);

        // Add the section to the database
        sectionDAO.addSection(testSection);

        // Retrieve the section from the database and check if it is added correctly
        Sections addedSecton = sectionDAO.getSectionById(id);
        assertEquals(testSection, addedSecton);
    }

    @Test
    public void testGetAllsections() {
        // Retrieve all sections from the database
        List<Sections> sections = sectionDAO.getAllSections();

        // Check if the returned list of sections is not empty
        assertFalse(sections.isEmpty());
    }

    @Test
    public void testGetsectionById() {
        // Create a test section
        UUID id = UUID.randomUUID();
        String name = "Storage 1";
        String inside = "Smart toy";
        int value = 5;
        int serchId = 1;
        Sections testSections = new Sections(id, name, inside, value, serchId);

        // Add the section to the database
        sectionDAO.addSection(testSections);

        // Retrieve the section by its id from the database
        Sections retrievedSection = sectionDAO.getSectionById(id);

        // Check if the retrieved section has the same id as the test section
        assertEquals(testSections.getId(), retrievedSection.getId());
    }

    @Test
    public void testUpdatesection() {
        // Create a test section
        UUID id = UUID.randomUUID();
        String name = "Section 1";
        String inside = "Smart toy";
        int value = 5;
        int serchId = 1;
        Sections testSection = new Sections(id, name, inside, value, serchId);

        // Add the section to the database
        sectionDAO.addSection(testSection);

        // Update the information of the section
        String updatedName = "Updated section";
        testSection.setName(updatedName);
        sectionDAO.updateSection(testSection);

        // Retrieve the section by its id from the database
        Sections retrievedStorage = sectionDAO.getSectionById(id);

        // Check if the name of the section has been updated
        assertEquals(updatedName, retrievedStorage.getName());
    }

    @Test
    public void testDeletesection() {
        // Create a test section
        UUID id = UUID.randomUUID();
        String name = "Section 1";
        String inside = "Smart toys";
        int value = 5;
        int serchId = 1;
        Sections testSection = new Sections(id, name, inside, value, serchId);

        // Add the section to the database
        sectionDAO.addSection(testSection);

        // Delete the section from the database
        sectionDAO.deleteSection(id);

        // Retrieve the section by its id from the database
        Sections deletedsection = sectionDAO.getSectionById(id);

        // Check if the section has been successfully deleted
        assertNull(deletedsection);
    }
}
