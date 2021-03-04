package org.example.services;

import org.example.dao.implementation.ParkDao;
import org.example.models.Forester;
import org.example.models.Plant;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkService {

    private ParkDao dao;

    public ParkService(ParkDao dao) {
        this.dao = dao;
    }

    public Optional<Plant> findById (int id) {
        return dao.findById(id);
    }

    public List<Plant> findAll() throws SQLException {
        return dao.findAll();
    }

    public void create(int foresterId, Plant plant) {
        dao.create(foresterId, plant);
    }

    public void update(int foresterId, int plantId, Map<String, String> params) {
        dao.update(foresterId, plantId, params);
    }

    public Plant deletePlant(int foresterId, int plantId) {
        return dao.delete(foresterId, plantId);
    }
    public void getStatistics() throws SQLException {

        List<Plant> allPlants = dao.findAll();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Here's a short report of how your plants are doing:");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("You have " + getSickPlants(allPlants).size() + " sick plant(s).");
        System.out.println("You have " + getNotTrimmedPlants(allPlants).size() + " plant(s) that need trimming.");
        System.out.println("You have " + getOldPlants(allPlants).size() + " plant(s) that need special care because of their old age.");

    }

    public List<Plant> getSickPlants(List<Plant> allPlants) throws SQLException {

        return allPlants.stream().filter(plant -> plant.getIs_sick() == 1).collect(Collectors.toList());

    }

    public List<Plant> getNotTrimmedPlants(List<Plant> allPlants) throws SQLException {

        return allPlants.stream().filter(plant -> plant.getIs_trimmed() == 0).collect(Collectors.toList());

    }

    public List<Plant> getOldPlants(List<Plant> allPlants) throws SQLException {

        return allPlants.stream().filter(plant -> plant.getAge() >= 20).collect(Collectors.toList());

    }

    public List<Forester> findAllForesters() throws SQLException {
        return dao.findAllForesters();
    }

    public List<String> getHistory() throws SQLException {
        return dao.getHistory();
    }


}
