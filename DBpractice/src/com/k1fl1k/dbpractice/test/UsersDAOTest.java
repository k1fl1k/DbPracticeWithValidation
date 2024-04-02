package com.k1fl1k.dbpractice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.k1fl1k.dbpractice.persistance.DAO.UsersDAO;
import com.k1fl1k.dbpractice.persistance.entity.Users;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsersDAOTest {

    private UsersDAO usersDAO;

    @BeforeEach
    public void setUp() {
        // Initialize the UsersDAO object before each test
        usersDAO = new UsersDAO();
    }

    @Test
    public void testAddUser() {
        // Create a test user
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        String post = "Developer";
        int searchId = 1;
        Users testUser = new Users(id, name, surname, post, searchId);

        // Add the user to the database
        usersDAO.addUser(testUser);

        // Retrieve the user from the database and check if it is added correctly
        Users addedUser = usersDAO.getUserById(id);
        assertEquals(testUser, addedUser);
    }

    @Test
    public void testGetAllUsers() {
        // Retrieve all users from the database
        List<Users> users = usersDAO.getAllUsers();

        // Check if the returned list of users is not empty
        assertFalse(users.isEmpty());
    }

    @Test
    public void testGetUserById() {
        // Create a test user
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        String post = "Developer";
        int searchId = 1;
        Users testUser = new Users(id, name, surname, post, searchId);

        // Add the user to the database
        usersDAO.addUser(testUser);

        // Retrieve the user by its id from the database
        Users retrievedUser = usersDAO.getUserById(id);

        // Check if the retrieved user has the same id as the test user
        assertEquals(testUser.getId(), retrievedUser.getId());
    }

    @Test
    public void testUpdateUser() {
        // Create a test user
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        String post = "Developer";
        int searchId = 1;
        Users testUser = new Users(id, name, surname, post, searchId);

        // Add the user to the database
        usersDAO.addUser(testUser);

        // Update the information of the user
        String updatedName = "Updated John";
        testUser.setName(updatedName);
        usersDAO.updateUser(testUser);

        // Retrieve the user by its id from the database
        Users retrievedUser = usersDAO.getUserById(id);

        // Check if the name of the user has been updated
        assertEquals(updatedName, retrievedUser.getName());
    }

    @Test
    public void testDeleteUser() {
        // Create a test user
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        String post = "Developer";
        int searchId = 1;
        Users testUser = new Users(id, name, surname, post, searchId);

        // Add the user to the database
        usersDAO.addUser(testUser);

        // Delete the user from the database
        usersDAO.deleteUser(id);

        // Retrieve the user by its id from the database
        Users deletedUser = usersDAO.getUserById(id);

        // Check if the user has been successfully deleted
        assertNull(deletedUser);
    }

}
