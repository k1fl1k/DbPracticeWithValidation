package com.k1fl1k.dbpractice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.k1fl1k.dbpractice.persistance.DAO.ToysDAO;
import com.k1fl1k.dbpractice.persistance.entity.Toys;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToysDAOTest {
    private ToysDAO toysDAO;

    @BeforeEach
    public void setUp() {
        // Initialize the ToysDAO object before each test
        toysDAO = new ToysDAO();
    }

    @Test
    public void testAddToy() {
        // Create a test toy
        UUID id = UUID.randomUUID();
        String type = "Toy Type";
        int value = 10;
        int price = 20;
        int searchId = 1;
        Toys testToy = new Toys(id, type, value, price, searchId);

        // Add the toy to the database
        toysDAO.addToy(testToy);

        // Retrieve the toy from the database and check if it is added correctly
        Toys addedToy = toysDAO.getToyById(id);
        assertEquals(testToy, addedToy);
    }

    @Test
    public void testGetAllToys() {
        // Retrieve all toys from the database
        List<Toys> toys = toysDAO.getAllToys();

        // Check if the returned list of toys is not empty
        assertFalse(toys.isEmpty());
    }

    @Test
    public void testGetToyById() {
        // Create a test toy
        UUID id = UUID.randomUUID();
        String type = "Toy Type";
        int value = 10;
        int price = 20;
        int searchId = 1;
        Toys testToy = new Toys(id, type, value, price, searchId);

        // Add the toy to the database
        toysDAO.addToy(testToy);

        // Retrieve the toy by its id from the database
        Toys retrievedToy = toysDAO.getToyById(id);

        // Check if the retrieved toy has the same id as the test toy
        assertEquals(testToy.getId(), retrievedToy.getId());
    }

    @Test
    public void testUpdateToy() {
        // Create a test toy
        UUID id = UUID.randomUUID();
        String type = "Toy Type";
        int value = 10;
        int price = 20;
        int searchId = 1;
        Toys testToy = new Toys(id, type, value, price, searchId);

        // Add the toy to the database
        toysDAO.addToy(testToy);

        // Update the information of the toy
        String updatedType = "Updated Toy Type";
        testToy.setType(updatedType);
        toysDAO.updateToy(testToy);

        // Retrieve the toy by its id from the database
        Toys retrievedToy = toysDAO.getToyById(id);

        // Check if the type of the toy has been updated
        assertEquals(updatedType, retrievedToy.getType());
    }

    @Test
    public void testDeleteToy() {
        // Create a test toy
        UUID id = UUID.randomUUID();
        String type = "Toy Type";
        int value = 10;
        int price = 20;
        int searchId = 1;
        Toys testToy = new Toys(id, type, value, price, searchId);

        // Add the toy to the database
        toysDAO.addToy(testToy);

        // Delete the toy from the database
        toysDAO.deleteToy(id);

        // Retrieve the toy by its id from the database
        Toys deletedToy = toysDAO.getToyById(id);

        // Check if the toy has been successfully deleted
        assertNull(deletedToy);
    }
}
