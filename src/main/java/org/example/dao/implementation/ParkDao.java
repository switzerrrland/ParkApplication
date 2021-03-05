package org.example.dao.implementation;

import org.example.dao.interfaces.Dao;
import org.example.models.Forester;
import org.example.models.Plant;

import java.sql.*;
import java.util.*;

import static org.example.utils.Constants.*;

import static org.example.utils.ConnectionUtils.createConnection;


public class ParkDao implements Dao<Plant> {

    private String dbPath;
    Statement statement;
    private Connection connection = null;

    public ParkDao(String dbPath) {
        this.dbPath = dbPath;
        try {
            connection = createConnection(dbPath);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Something is wrong with database connection :(");
        }

    }

    @Override
    public Optional<Plant> findById(int id) {
        String query = String.format(String.valueOf(FIND_BY_ID_QUERY),id);
        try {
            ResultSet res = statement.executeQuery(query);
            String name = res.getString("name");
            int age = res.getInt("age");
            int is_trimmed = res.getInt("is_trimmed");
            int is_sick = res.getInt("is_sick");

            Plant plant = new Plant(name, age, is_trimmed, is_sick);
            return Optional.of(plant);

        } catch (SQLException ex) {
            System.out.println("Plant with such id doesn't exist");
        }

        return Optional.empty();
    }

    @Override
    public List<Plant> findAll() throws SQLException {
        List<Plant> plants = new ArrayList<>();

        ResultSet plantsTable = statement.executeQuery(String.valueOf(FIND_ALL_QUERY));
        while (plantsTable.next()) {

            String name = plantsTable.getString("name");
            int age = plantsTable.getInt("age");
            int is_trimmed = plantsTable.getInt("is_trimmed");
            int is_sick = plantsTable.getInt("is_sick");

            Plant plant = new Plant(name, age, is_trimmed, is_sick);
            plants.add(plant);
        }
        return plants;
    }

    @Override
    public void create(int foresterId, Plant plant) {

        //Forester forester = findForesterById(foresterId).get();
        String query = String.format(String.valueOf(INSERT_QUERY), plant.getName(), plant.getAge(), plant.getIs_trimmed(), plant.getIs_sick());
        try {
            statement.execute(query);
            ResultSet plantIdRes = statement.executeQuery("select max(id) as max_id from plants");
            int plantId = plantIdRes.getInt("max_id");
            String historyQuery = String.format(String.valueOf(INSERT_HISTORY_QUERY), foresterId, plantId, "create");
            statement.execute(historyQuery);
        } catch (SQLException ex) {
            System.out.println("Insertion failure");
        }

    }

    @Override
    public void update(int foresterId, int plantId, Map<String, String> params) {//только все значения
        Optional<Plant> res = findById(plantId);

        if (!res.isPresent()) {
            System.out.println("Plant with id = " + plantId + " doesn't exist");
            return;
        }

        if (!params.get("is_trimmed").equals("0") && !params.get("is_trimmed").equals("1")) {
            throw new IllegalArgumentException("Is_trimmed can only be 0 or 1.");
        }
        if (!params.get("is_sick").equals("0") && !params.get("is_sick").equals("1")) {
            throw new IllegalArgumentException("Is_sick can only be 0 or 1.");
        }

        String query = String.format(String.valueOf(UPDATE_QUERY), res.get().getName(), res.get().getAge(),
                Integer.parseInt(params.get("is_trimmed")), Integer.parseInt(params.get("is_sick")), plantId);


        try {
            statement.execute(query);
            String historyQuery = String.format(String.valueOf(INSERT_HISTORY_QUERY), foresterId, plantId, "update");
            statement.execute(historyQuery);
        } catch (SQLException ex) {
            System.out.println("Update failed: Plant with id = " + plantId + " doesn't exist");
        }


    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public Plant delete(int foresterId, int plantId) {

        Plant delPlant = findById(plantId).get();//NoSuchElementException: No value present
        String query = String.format(String.valueOf(DELETE_QUERY), plantId);
        try {
            statement.execute(query);
            String historyQuery = String.format(String.valueOf(INSERT_HISTORY_QUERY), foresterId, plantId, "delete");
            statement.execute(historyQuery);

        } catch (SQLException ex) {
            System.out.println("Delete failed: Plant with id = " + plantId + " doesn't exist");
        }

        return delPlant;

    }

    public Optional<Forester> findForesterById(int id) {
        String query = String.format(String.valueOf(FIND_BY_ID_QUERY),id);
        try {
            ResultSet forestersTable = statement.executeQuery(query);
            String firstName = forestersTable.getString("first_name");
            String lastName = forestersTable.getString("last_name");
            String middleName = forestersTable.getString("middle_name");
            String phoneNumber = forestersTable.getString("phone_number");
            String dateOfBirth = forestersTable.getString("date_of_birth");

            Forester forester = new Forester(firstName, lastName, middleName, phoneNumber, dateOfBirth);
            return Optional.of(forester);

        } catch (SQLException ex) {
            System.out.println("Plant with such id doesn't exist");
        }

        return Optional.empty();
    }

    public List<Forester> findAllForesters() throws SQLException {
        List<Forester> foresters = new ArrayList<>();

        ResultSet forestersTable = statement.executeQuery("select * from foresters");
        while (forestersTable.next()) {

            String firstName = forestersTable.getString("first_name");
            String lastName = forestersTable.getString("last_name");
            String middleName = forestersTable.getString("middle_name");
            String phoneNumber = forestersTable.getString("phone_number");
            String dateOfBirth = forestersTable.getString("date_of_birth");

            Forester forester = new Forester(firstName, lastName, middleName, phoneNumber, dateOfBirth);
            foresters.add(forester);
        }
        return foresters;
    }

    public List<String> getHistory() throws SQLException {

        List<String> historyRecords = new ArrayList<>();

        ResultSet historyResultSet = statement.executeQuery("select * from history");
        while (historyResultSet.next()) {
            int foresterId = historyResultSet.getInt("forester_id");
            int plantId = historyResultSet.getInt("plant_id");
            String action = historyResultSet.getString("action");

            historyRecords.add(foresterId + " " + plantId + " " + action);
        }

        return historyRecords;
    }
}
