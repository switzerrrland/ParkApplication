package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class App {


    public static void main( String[] args ) throws SQLException, IOException {

        ParkDao parkDao = new ParkDao();

        System.out.println("Choose your action:");
        System.out.println("1 - Create a plant");
        System.out.println("2 - Find a plant by id");
        System.out.println("3 - Delete a plant by id");
        System.out.println("4 - Update a plant by id");
        System.out.println("5 - List all plants");
        System.out.println("0 - Exit");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cmd = null;
        while ((cmd = reader.readLine()) != "0") {

            switch (cmd) {
                case "1":
                    System.out.println("Let's create a plant! Enter a name:");
                    String name = reader.readLine();
                    System.out.println("Enter an age:");
                    int age = Integer.parseInt(reader.readLine());
                    System.out.println("Is plant trimmed? \r\n1 - yes \r\n0 - no");
                    int isTrimmed = Integer.parseInt(reader.readLine());
                    System.out.println("Is plant sick? \r\n1 - yes \r\n0 - no");
                    int isSick = Integer.parseInt(reader.readLine());

                    Plant plant = new Plant(name, age, isTrimmed, isSick);
                    parkDao.create(plant);
                    System.out.println("New plant is successfully created:\n" + plant.toString());
                    System.out.println("Anything else? Enter another command:");

                    break;
                case "2":
                    System.out.println("Enter id:");
                    int id = Integer.parseInt(reader.readLine());
                    Plant result = parkDao.findById(id).get();
                    System.out.println("This is your plant:\n" + result.toString());
                    System.out.println("Anything else? Enter another command:");

                    break;
                case "3":
                    System.out.println("Enter id:");
                    int plantId = Integer.parseInt(reader.readLine());
                    parkDao.deleteById(plantId);
                    System.out.println("Plant with id = " + plantId + " deleted");
                    System.out.println("Anything else? Enter another command:");

                    break;
                case "4":
                    System.out.println("Enter id:");
                    int plId = Integer.parseInt(reader.readLine());
                    Map<String, String> plantParams = new HashMap<>();
                    System.out.println("You need to enter all the plant's parameters to update it.\nEnter  name:");
                    String newName = reader.readLine();
                    System.out.println("Enter age:");
                    String newAge = reader.readLine();
                    System.out.println("Is plant trimmed? \r\n1 - yes \r\n0 - no");
                    String isNewTrimmed = reader.readLine();
                    System.out.println("Is plant sick? \r\n1 - yes \r\n0 - no");
                    String isNewSick = reader.readLine();

                    plantParams.put("name", newName);
                    plantParams.put("age", newAge);
                    plantParams.put("is_trimmed", isNewTrimmed);
                    plantParams.put("is_sick", isNewSick);

                    parkDao.update(plId, plantParams);
                    System.out.println("Update successful");
                    System.out.println("Anything else? Enter another command:");

                    break;
                case "5":
                    System.out.println("Here are all the plants:");
                    List<Plant> allPlants = parkDao.findAll();
                    for (Plant p : allPlants) {
                        System.out.println(p);
                    }
                    System.out.println("Anything else? Enter another command:");


                    break;
                case "0":
                    System.out.println("Okay, bye :( ");
                    return;

                default:
                    System.out.println("Enter correct command >_<");
                    break;
            }
        }

        //Plant plant = new Plant(1000, "loltree", 10000, 1, 0);
        //parkDao.create(plant);
        //parkDao.deleteById(1000);

        //Optional<Plant> plant1000 = parkDao.findById(77777);

        /*if (plant1000.isPresent()) {

            System.out.println("This is your plant:");
            System.out.println(plant1000.get());
        } else {
            System.out.println("Plant with id = 1000 doesn't exist");
        }*/

        //List<Plant> plants = parkDao.findAll();
        //Map<String, String> map = new HashMap<>();
        //map.put("name", "kektree");
        //map.put("age", "1488");
        //map.put("is_trimmed", "0");
        //map.put("is_sick", "1");


        //parkDao.update(1000, map);

        ConnectionUtils.closeConnection();
        //System.out.println(plant);


        /*for (Plant p: plants) {
            System.out.println(p);
        }*/
   }



}


