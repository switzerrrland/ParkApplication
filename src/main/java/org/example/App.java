package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;
import static org.example.Constants.*;

public class App {


    public static void main( String[] args ) throws SQLException, IOException {

        ParkDao parkDao = new ParkDao(PRODUCTION_DB_PATH.toString());
        System.out.println(PRODUCTION_DB_PATH.toString());

        System.out.println("Choose your action:");
        System.out.println("1 - Create plant");
        System.out.println("2 - Find plant by id");
        System.out.println("3 - Delete plant by id");
        System.out.println("4 - Update plant by id");
        System.out.println("5 - List all plants");
        System.out.println("0 - Exit");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cmd = null;
        while ((cmd = reader.readLine()) != "0") {

            switch (cmd) {
                case "1":
                    System.out.println("Let's create a new plant! \nEnter plant's name:");
                    String name = reader.readLine();
                    System.out.println("Enter age:");
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
                    System.out.println("Enter id of the plant you're looking for:");
                    int id = Integer.parseInt(reader.readLine());
                    Plant result = parkDao.findById(id).get();
                    System.out.println("This is your plant:\n" + result.toString());
                    System.out.println("Anything else? Enter another command:");

                    break;
                case "3":
                    System.out.println("Enter id of the plant you want to delete:");
                    int plantId = Integer.parseInt(reader.readLine());
                    parkDao.deleteById(plantId);
                    System.out.println("Plant with id = " + plantId + " deleted");
                    System.out.println("Anything else? Enter another command:");

                    break;
                case "4":
                    System.out.println("Enter id of the plant you want to update:");
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
                    System.out.println("Okay, bye");
                    return;

                default:
                    System.out.println("Unknown command. Enter correct command from the list above:");
                    break;
            }
        }

        ConnectionUtils.closeConnection();

   }



}


