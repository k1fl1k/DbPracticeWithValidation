package com.k1fl1k.dbpractice.persistance.service;

import com.k1fl1k.dbpractice.persistance.DAO.SectionDAO;
import com.k1fl1k.dbpractice.persistance.DAO.StorageDAO;
import com.k1fl1k.dbpractice.persistance.DAO.ToysDAO;
import com.k1fl1k.dbpractice.persistance.DAO.UsersDAO;
import com.k1fl1k.dbpractice.persistance.entity.Sections;
import com.k1fl1k.dbpractice.persistance.entity.Storage;
import com.k1fl1k.dbpractice.persistance.entity.Toys;
import com.k1fl1k.dbpractice.persistance.entity.Users;
import java.util.List;
import java.util.UUID;

public class DBService {

    private final SectionDAO sectionDAO;
    private final StorageDAO storageDAO;
    private final ToysDAO toysDAO;
    private final UsersDAO usersDAO;

    public DBService(SectionDAO sectionDAO, StorageDAO storageDAO, ToysDAO toysDAO,
        UsersDAO usersDAO) {
        this.sectionDAO = sectionDAO;
        this.storageDAO = storageDAO;
        this.toysDAO = toysDAO;
        this.usersDAO = usersDAO;
    }

    // Section CRUD operations
    public void addSection(UUID id, String name, String inside, int value, int searchId) {
        Sections section = new Sections(id, name, inside,value,searchId);
        sectionDAO.addSection(section);
    }

    public List<Sections> getAllSections() {
        return sectionDAO.getAllSections();
    }

    public Sections getSectionById(UUID id) {
        return sectionDAO.getSectionById(id);
    }

    public void updateSection(UUID id, String name, String inside, int value, int searchId) {
        Sections section = new Sections(id, name, inside,value,searchId);
        sectionDAO.updateSection(section);
    }

    public void deleteSection(UUID id) {
        sectionDAO.deleteSection(id);
    }

    // Storage CRUD operations
    public void addToy(Toys toy) {
        toysDAO.addToy(toy);
    }

    public List<Toys> getAllToys() {
        return toysDAO.getAllToys();
    }

    public Toys getToyById(UUID id) {
        return toysDAO.getToyById(id);
    }

    public void updateToy(Toys toy) {
        toysDAO.updateToy(toy);
    }

    public void deleteToy(UUID id) {
        toysDAO.deleteToy(id);
    }

    // Toys CRUD operations

    // Users CRUD operations
    public void addUser(UUID id, String name, String surname, String post, int searchId) {
        Users user = new Users(id, name, surname, post, searchId);
        usersDAO.addUser(user);
    }

    public List<Users> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    public Users getUserById(UUID id) {
        return usersDAO.getUserById(id);
    }

    public void updateUser(UUID id, String name, String surname, String post, int searchId) {
        Users user = new Users(id, name, surname, post, searchId);
        usersDAO.updateUser(user);
    }

    public void deleteUser(UUID id) {
        usersDAO.deleteUser(id);
    }
}
