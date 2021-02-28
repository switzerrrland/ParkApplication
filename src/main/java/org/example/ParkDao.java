package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.example.Constants.*;

import static org.example.ConnectionUtils.createConnection;


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
            System.out.println("Item with such id doesn't exist");
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
    public void create(Plant plant) {
        String query = String.format(String.valueOf(INSERT_QUERY), plant.getName(), plant.getAge(), plant.getIs_trimmed(), plant.getIs_sick());
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            System.out.println("Insertion failure");
            }

    }

    @Override
    public void update(int id, Map<String, String> params) {//только все значения
        Optional<Plant> res = findById(id);

        if (!res.isPresent()) {
            System.out.println("Plant with id = " + id + " doesn't exist");
            return;
        }

        if (params.get("is_trimmed") != "0" && params.get("is_trimmed") != "1") {
            throw new IllegalArgumentException("Is_trimmed can only be 0 or 1.");
        }
        if (params.get("is_sick") != "0" && params.get("is_sick") != "1") {
            throw new IllegalArgumentException("Is_sick can only be 0 or 1.");
        }

        String query = String.format(String.valueOf(UPDATE_QUERY), params.get("name"), Integer.parseInt(params.get("age")),
                Integer.parseInt(params.get("is_trimmed")), Integer.parseInt(params.get("is_sick")), id);

        try {
            statement.execute(query);
        } catch (SQLException ex) {
            System.out.println("Update failed: Plant with id = " + id + " doesn't exist");
        }


    }

    protected Statement getStatement() {
        return statement;
    }

    @Override
    public Plant deleteById(int id) {
        Plant delPlant = findById(id).get();
        String query = String.format(String.valueOf(DELETE_QUERY), id);
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            System.out.println("Delete failed: Plant with id = " + id + " doesn't exist");
        }

        return delPlant;



    }
}
