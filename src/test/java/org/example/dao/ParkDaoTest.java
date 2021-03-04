package org.example.dao;


import org.example.dao.implementation.ParkDao;
import org.example.models.Forester;
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

    public ParkDaoTest() throws SQLException {
    }

    @BeforeMethod
    public void fillTestDb() throws SQLException {


        statement.execute("CREATE TABLE IF NOT EXISTS plants (id INTEGER NOT NULL UNIQUE, name TEXT NOT NULL, age INTEGER NOT NULL, is_trimmed	INTEGER NOT NULL,is_sick	INTEGER NOT NULL, PRIMARY KEY(id AUTOINCREMENT));");
        statement.execute("CREATE TABLE IF NOT EXISTS foresters (id INTEGER NOT NULL UNIQUE, first_name TEXT NOT NULL, last_name TEXT NOT NULL, middle_name	TEXT NOT NULL, phone_number	TEXT NOT NULL, date_of_birth TEXT NOT NULL, PRIMARY KEY(id AUTOINCREMENT));");
        statement.execute("CREATE TABLE IF NOT EXISTS history (forester_id INTEGER NOT NULL, plant_id INTEGER NOT NULL, action TEXT NOT NULL);");
        statement.execute("INSERT INTO plants (name, age, is_trimmed, is_sick) VALUES ('oak',100,1,0);");
        statement.execute("INSERT INTO plants (name, age, is_trimmed, is_sick) VALUES ('birch',15,0,0);");
        statement.execute("INSERT INTO plants (name, age, is_trimmed, is_sick) VALUES ('ash tree',17,1,1);");
        statement.execute("INSERT INTO foresters (first_name, last_name,middle_name,phone_number,date_of_birth) VALUES ('Stepan','Philippovich','Dubov','+79601236547','01.04.1990');");
        statement.execute("INSERT INTO foresters (first_name, last_name,middle_name,phone_number,date_of_birth) VALUES ('Evgenii','Vladimirovich','Nikitin','+79849874561','17.06.1984');");
        statement.execute("INSERT INTO foresters (first_name, last_name,middle_name,phone_number,date_of_birth) VALUES ('Philipp','Markovich','Kot','+79991234567','08.08.2000');");

    }
    @AfterMethod
    public void cleanTestDb() throws SQLException {

        statement.execute("DROP TABLE IF EXISTS plants");
        statement.execute("DROP TABLE IF EXISTS foresters");
        statement.execute("DROP TABLE IF EXISTS history");


    }

    @Test
    public void createPlantTest() throws SQLException {
        Plant p = new Plant("sequoia", 2000, 0, 0);
        int foresterId = 1;
        dao.create(foresterId ,p);
        List<Plant> plants = dao.findAll();
        Assert.assertTrue(plants.contains(p));

    }

    @Test
    public void updatePlantTest() {
        Plant expectedPlant = new Plant("ash tree", 17, 0, 0);

        int foresterId = 2;

        Map<String,String> params = new HashMap<>();
        params.put("is_trimmed", "0");
        params.put("is_sick", "0");

        dao.update(foresterId,3, params);
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

        int foresterId = 3;

        Plant deletedPlant = dao.delete(foresterId, 1);

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

    @Test
    public void findAllForestersTest() throws SQLException {

        List<Forester> expectedForesters = new ArrayList<>();
        Forester forester1 = new Forester("Stepan","Philippovich","Dubov","+79601236547","01.04.1990");
        Forester forester2 = new Forester("Evgenii","Vladimirovich","Nikitin","+79849874561","17.06.1984");
        Forester forester3 = new Forester("Philipp","Markovich","Kot","+79991234567","08.08.2000");

        expectedForesters.add(forester1);
        expectedForesters.add(forester2);
        expectedForesters.add(forester3);

        List<Forester> actualForesters = dao.findAllForesters();
        Assert.assertEquals(expectedForesters, actualForesters);

    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
        public void incorrectPlantTest() {
        int foresterId = 1;
        Plant incorrectPlant = new Plant("cherry tree", 213, 5, 789);
        dao.create(foresterId, incorrectPlant);

    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void incorrectPlantUpdateTest() {
        int foresterId = 2;

        Map<String, String> incorrectParams = new HashMap<>();
        incorrectParams.put("is_trimmed", "1");
        incorrectParams.put("is_sick", "I'm not even a number");

        dao.update(foresterId,1, incorrectParams);

    }

    @Test
    public void getHistoryRecordsTest() throws SQLException {

        int foresterId = 3;

        dao.delete(foresterId, 1);
        dao.create(foresterId, new Plant("sakura", 22, 1, 1));

        Map<String, String> params = new HashMap<>();
        params.put("is_trimmed", "1");
        params.put("is_sick", "1");

        dao.update(foresterId, 2, params);

        String historyRecord1 = "3 1 delete";
        String historyRecord2 = "3 4 create";
        String historyRecord3 = "3 2 update";

        List<String> expectedHistoryRecords = Arrays.asList(historyRecord1, historyRecord2, historyRecord3);
        List<String> actualHistoryRecords = dao.getHistory();

        Assert.assertEquals(expectedHistoryRecords, actualHistoryRecords);

    }


}

