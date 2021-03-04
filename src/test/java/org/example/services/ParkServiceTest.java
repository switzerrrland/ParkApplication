package org.example.services;

import org.example.dao.implementation.ParkDao;
import org.example.models.Plant;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkServiceTest {

    ParkDao dao = new ParkDao("jdbc:sqlite:C:/Users/user/Desktop/ParkTest.db");
    ParkService service = new ParkService(dao);

    Statement statement = dao.getStatement();

    public ParkServiceTest() throws SQLException {
    }

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
    public void getSickPlantsTest() throws SQLException {

        List<Plant> actualSick = service.getSickPlants(dao.findAll());
        Plant sickPlant = new Plant("ash tree", 17, 1, 1);
        List<Plant> expectedSick = new ArrayList<>();
        expectedSick.add(sickPlant);
        Assert.assertEquals(actualSick, expectedSick);

    }

    @Test
    public void getTrimmedPlantsTest() throws SQLException {

        List<Plant> actualNotTrimmed = service.getNotTrimmedPlants((dao.findAll()));
        Plant notTrimmedPlant = new Plant("birch", 15, 0, 0);
        List<Plant> expectedNotTrimmed = new ArrayList<>();
        expectedNotTrimmed.add(notTrimmedPlant);
        Assert.assertEquals(actualNotTrimmed, expectedNotTrimmed);

    }

    @Test
    public void getOldPlantsTest() throws SQLException {

        List<Plant> actualOld = service.getOldPlants((dao.findAll()));
        Plant oldPlant = new Plant("oak", 100, 1, 0);
        List<Plant> expectedOld = new ArrayList<>();
        expectedOld.add(oldPlant);
        Assert.assertEquals(actualOld, expectedOld);

    }


}
