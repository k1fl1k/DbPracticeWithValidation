package com.k1fl1k.dbpractice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.k1fl1k.dbpractice.persistance.DAO.StorageDAO;
import com.k1fl1k.dbpractice.persistance.entity.Storage;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class StorageDAOTest {

    private StorageDAO storageDAO;

    @BeforeEach
    public void setUp() {
        // Initialize the StorageDAO object before each test
        storageDAO = new StorageDAO();
    }

    @Test
    public void testAddstorage() {
        // Create a test storage
        UUID id = UUID.randomUUID();
        String name = "Storage 1";
        String street = "street 1";
        int sections = 5;
        int serchId = 1;
        Storage testStorage = new Storage(id, name, street, sections, serchId);

        // Add the storage to the database
        storageDAO.addStorage(testStorage);

        // Retrieve the storage from the database and check if it is added correctly
        Storage addedStorage = storageDAO.getStorageById(id);
        assertEquals(testStorage, addedStorage);
    }

    @Test
    public void testGetAllstorages() {
        // Retrieve all storages from the database
        List<Storage> storages = storageDAO.getAllStorage();

        // Check if the returned list of storages is not empty
        assertFalse(storages.isEmpty());
    }

    @Test
    public void testGetstorageById() {
        // Create a test storage
        UUID id = UUID.randomUUID();
        String name = "Storage 1";
        String street = "Street 1";
        int sections = 5;
        int serchId = 1;
        Storage testStorage = new Storage(id, name, street, sections, serchId);

        // Add the storage to the database
        storageDAO.addStorage(testStorage);

        // Retrieve the storage by its id from the database
        Storage retrievedstorage = storageDAO.getStorageById(id);

        // Check if the retrieved storage has the same id as the test storage
        assertEquals(testStorage.getId(), retrievedstorage.getId());
    }

    @Test
    public void testUpdatestorage() {
        // Create a test storage
        UUID id = UUID.randomUUID();
        String name = "Storage 1";
        String street = "Street 1";
        int sections = 5;
        int serchId = 1;
        Storage testStorage = new Storage(id, name, street, sections, serchId);

        // Add the storage to the database
        storageDAO.addStorage(testStorage);

        // Update the information of the storage
        String updatedName = "Updated storage";
        testStorage.setName(updatedName);
        storageDAO.updateStorage(testStorage);

        // Retrieve the storage by its id from the database
        Storage retrievedStorage = storageDAO.getStorageById(id);

        // Check if the name of the storage has been updated
        assertEquals(updatedName, retrievedStorage.getName());
    }

    @Test
    public void testDeletestorage() {
        // Create a test storage
        UUID id = UUID.randomUUID();
        String name = "Storage 1";
        String street = "Street 1";
        int sections = 5;
        int serchId = 1;
        Storage teststorage = new Storage(id, name, street, sections, serchId);

        // Add the storage to the database
        storageDAO.addStorage(teststorage);

        // Delete the storage from the database
        storageDAO.deleteStorage(id);

        // Retrieve the storage by its id from the database
        Storage deletedstorage = storageDAO.getStorageById(id);

        // Check if the storage has been successfully deleted
        assertNull(deletedstorage);
    }

}
