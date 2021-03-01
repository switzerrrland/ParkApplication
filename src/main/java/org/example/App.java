package org.example;

import org.example.dao.implementation.ParkDao;
import org.example.models.Plant;
import org.example.services.ParkService;
import org.example.utils.ConnectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;
import static org.example.utils.Constants.*;

public class App {


    public static void main( String[] args ) throws SQLException, IOException {

        ParkDao parkDao = new ParkDao(PRODUCTION_DB_PATH.toString());
        ParkService parkService = new ParkService(parkDao);
        System.out.println(PRODUCTION_DB_PATH.toString());

        System.out.println("Choose your action:");
        System.out.println("1 - Create plant");
        System.out.println("2 - Find plant by id");
        System.out.println("3 - Delete plant by id");
        System.out.println("4 - Update plant by id");
        System.out.println("5 - List all plants");
        System.out.println("6 - See statistics");
        System.out.println("0 - Exit");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cmd = null;
        while ((cmd = reader.readLine()) != "0") {

            switch (cmd) {
                case "1":
                    System.out.println("Let's create a new plant! \nEnter plant's name:");
                    String name = reader.readLine();
                    System.out.println("Enter age:");
                    Integer age = null;
                        try {
                            age = Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException ex) {
                            System.out.println("Age must be a number");
                            return;
                        }

                    System.out.println("Is plant trimmed? \r\n1 - yes \r\n0 - no");
                    Integer isTrimmed = null;
                        try {
                            isTrimmed = Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException ex) {
                            System.out.println("Enter a number");
                            return;
                        }


                    if (isTrimmed != 0 && isTrimmed != 1) {
                        System.out.println("Enter 0 or 1.");
                        return;
                    }

                    System.out.println("Is plant sick? \r\n1 - yes \r\n0 - no");
                    Integer isSick = null;
                        try {
                            isSick = Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException ex) {
                            System.out.println("Enter a number");
                            return;
                        }

                    if (isSick != 0 && isSick != 1) {
                        System.out.println("Enter 0 or 1.");
                        return;
                    }

                    Plant plant = new Plant(name, age, isTrimmed, isSick);
                    parkService.create(plant);
                    System.out.println("New plant is successfully created:\n" + plant.toString());
                    System.out.println("Anything else? Enter another command:");

                    break;
                case "2":
                    System.out.println("Enter id of the plant you're looking for:");
                    int id = Integer.parseInt(reader.readLine());
                    Optional<Plant> resultPlant = parkService.findById(id);
                    if (resultPlant.isPresent()) {
                        System.out.println("This is your plant:\n" + resultPlant.toString());
                    }


                    System.out.println("Anything else? Enter another command:");

                    break;
                case "3":
                    System.out.println("Enter id of the plant you want to delete:");
                    int plantId = Integer.parseInt(reader.readLine());
                    parkService.deleteById(plantId);
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
                        try {
                            Integer.parseInt(newAge);
                        } catch (NumberFormatException ex) {
                            System.out.println("Age must be a number");
                            return;
                        }
                    System.out.println("Is plant trimmed? \r\n1 - yes \r\n0 - no");
                    String isNewTrimmed = reader.readLine();
                        if (!isNewTrimmed.equals("0") && !isNewTrimmed.equals("1")) {
                            System.out.println("Enter 0 or 1.");
                            return;
                    }
                    System.out.println("Is plant sick? \r\n1 - yes \r\n0 - no");
                    String isNewSick = reader.readLine();
                        if (!isNewSick.equals("0") && !isNewSick.equals("1")) {
                            System.out.println("Enter 0 or 1.");
                            return;
                    }

                    plantParams.put("name", newName);
                    plantParams.put("age", newAge);
                    plantParams.put("is_trimmed", isNewTrimmed);
                    plantParams.put("is_sick", isNewSick);

                    parkService.update(plId, plantParams);
                    System.out.println("Update successful");
                    System.out.println("Anything else? Enter another command:");

                    break;
                case "5":
                    System.out.println("Here are all the plants:");
                    List<Plant> allPlants = parkService.findAll();
                    for (Plant p : allPlants) {
                        System.out.println(p);
                    }
                    System.out.println("Anything else? Enter another command:");

                    break;
                case "6":
                    parkService.getStatistics();
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


