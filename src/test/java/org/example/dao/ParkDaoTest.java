package org.example.dao;

import org.example.dao.implementation.ParkDao;
import org.example.models.Plant;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ParkDaoTest {

    ParkDao dao = new ParkDao("jdbc:sqlite:C:/Users/user/Desktop/ParkTest.db");
    Statement statement = dao.getStatement();

    @BeforeMethod
    public void fillTestDb() throws SQLException {


        statement.execute("CREATE TABLE IF NOT EXISTS plants (id INTEGER NOT NULL UNIQUE, name TEXT NOT NULL, age INTEGER NOT NULL, is_trimmed	INTEGER NOT NULL,is_sick	INTEGER NOT NULL, PRIMARY KEY(id AUTOINCREMENT));");
        statement.execute("INSERT INTO plants (name, age, is_trimmed, is_sick) VALUES ('oak',100,1,0);");
        statement.execute("INSERT INTO plants (name, age, is_trimmed, is_sick) VALUES ('birch',15,0,0);");
        statement.execute("INSERT INTO plants (name, age, is_trimmed, is_sick) VALUES ('ash tree',17,1,1);");

    }
    @AfterMethod
    public void cleanTestDb() throws SQLException {

        statement.execute("DROP TABLE IF EXISTS plants");


    }

    @Test
    public void createPlantTest() throws SQLException {
        Plant p = new Plant("sequoia", 2000, 0, 0);
        dao.create(p);
        List<Plant> plants = dao.findAll();
        Assert.assertTrue(plants.contains(p));


    }

    @Test
    public void updatePlantTest() {
        Plant expectedPlant = new Plant("sakura", 18, 0, 0);
        Map<String,String> params = new HashMap<>();
        params.put("name", "sakura");
        params.put("age", "18");
        params.put("is_trimmed", "0");
        params.put("is_sick", "0");

        dao.update(3, params);
        Plant updatedPlant = dao.findById(3).get();
        Assert.assertEquals(updatedPlant, expectedPlant);

    }

    @Test
    public void findPlantTest() throws SQLException {
        Optional<Plant> expPlant = dao.findById(2);
        Plant actPlant = new Plant("birch", 15, 0, 0);
        Assert.assertEquals(expPlant.get(), actPlant);

    }

    @Test
    public void deletePlantTest() throws SQLException {
        Plant deletedPlant = dao.deleteById(1);
        List<Plant> plants = dao.findAll();
        Assert.assertFalse(plants.contains(deletedPlant));


    }

    @Test
    public void findAllPlantsTest() throws SQLException {
        List<Plant> expectedPlants = new ArrayList<>();
        Plant expPlant1 = new Plant("oak",100,1,0);
        Plant expPlant2 = new Plant("birch", 15, 0, 0);
        Plant expPlant3 = new Plant("ash tree",17,1,1);
        expectedPlants.add(expPlant1);
        expectedPlants.add(expPlant2);
        expectedPlants.add(expPlant3);

        List<Plant> actualPlants = dao.findAll();
        Assert.assertEquals(expectedPlants, actualPlants);

        }


    @Test(expectedExceptions = {IllegalArgumentException.class})
        public void incorrectPlantTest() {
        Plant incorrectPlant = new Plant("cherry tree", 213, 5, 789);
        dao.create(incorrectPlant);

    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void incorrectPlantUpdateTest() {
        Map<String, String> incorrectParams = new HashMap<>();
        incorrectParams.put("name", "cherry tree");
        incorrectParams.put("age", "20");
        incorrectParams.put("is_trimmed", "14");
        incorrectParams.put("is_sick", "I'm not even a number");

        dao.update(1, incorrectParams);

    }


    }

