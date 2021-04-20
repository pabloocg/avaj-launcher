package avaj.Simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import avaj.Airplanes.*;
import avaj.Tower.*;

/*
    TO DO:
    - Algoritmo para saber el tiempo dependiendo de las coordenadas.
*/

public class Main {

    /**
     * Gets the first line of the scenario. Return the number of simulations to run,
     * parsing the string to int.
     **/
    private static int parseNSimulationLine(String first_line) {
        int ret = 0;
        try {
            ret = Integer.parseInt(first_line);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect format of number of simulations to run.");
            System.exit(1);
        }
        return ret;
    }

    private static Boolean isCorrectCoordinate(int value) {

        if (value < 0) {
            return false;
        }
        return true;
    }

    private static Boolean isCorrectHeightCoordinate(int value) {

        if (value <= 0 || value >= 100) {
            return false;
        }
        return true;
    }

    /**
     * Parse each line of the scenario, check the correct format.
     **/
    private static String[] parseLineOfFlyable(String line) {
        String[] ret;

        ret = line.split(" ");
        if (ret.length != 5) {
            System.out.println("Some Line have incorrect format.\nFormat: TYPE NAME LONGITUDE LATITUDE HEIGHT.");
            System.exit(1);
        }
        // Check correct type of airplane
        if (!(ret[0].equals("Helicopter") || ret[0].equals("Baloon") || ret[0].equals("JetPlane"))) {
            System.out.println("Some type of Airplane is incorrect.");
            System.exit(1);
        }
        try {
            if (!isCorrectCoordinate(Integer.parseInt(ret[2])) || !isCorrectCoordinate(Integer.parseInt(ret[3]))) {
                System.out.println(
                        "Line: " + line + "\nError: Some coordinate is incorrect, only positive numbers are valid.");
                System.exit(1);
            }
            if (!isCorrectHeightCoordinate(Integer.parseInt(ret[4]))) {
                System.out.println("Line: " + line
                        + "\nError: Height coordinate is incorrect, only numbers in range 0..100 are valid.");
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.out.println(
                    "Line: " + line + "\nError: Some coordinate is incorrect, only positive numbers are valid.");
            System.exit(1);
        }
        return ret;
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Needs the scenario file as argument to run the program.");
            System.exit(1);
        }
        try {
            String[] options;
            int number_of_simulations;
            Scanner scan = new Scanner(new File(args[0]));
            WeatherTower tower = new WeatherTower();
            ArrayList<Flyable> machines = new ArrayList<>();

            number_of_simulations = parseNSimulationLine(scan.nextLine());
            while (scan.hasNextLine()) {
                options = parseLineOfFlyable(scan.nextLine());
                machines.add(AircraftFactory.newAircraft(options[0], options[1], Integer.parseInt(options[2]),
                        Integer.parseInt(options[3]), Integer.parseInt(options[4])));
            }
            scan.close();
            for (Flyable flyable : machines) {
                flyable.registerTower(tower);
            }
            for (int i = 0; i < number_of_simulations; i++) {
                tower.changeWeather();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + args[0] + " not found.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something go wrong");
            e.printStackTrace();
        }
    }

}
