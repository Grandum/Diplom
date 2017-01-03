package Program;

import Program.Flight.*;
import Program.passanger.*;

import java.sql.SQLException;
import java.util.Scanner;

import static Program.Login.accessLevel;

public class Menu {

    public static void menu() throws SQLException {

        Scanner sc = new Scanner(System.in);

        if (accessLevel >= 2) {

            System.out.println("\n" + "Select option:");
            System.out.println("1.Flight Info" + "\n" + "2.Passenger Info" + "\n" + "3.Add Flight" + "\n" + "4.Add Passenger" + "\n" + "5.Flight status" + "\n" + "6.Delete Flight" + "\n" + "7.Delete Passenger" + "\n" + "9.Exit program");
            String option = sc.nextLine();

            if (option.equals("1")) {
                FlightInfo.flightInfo();
            } else if (option.equals("2")) {
                PassengersInfo.passengerInfo();
            } else if (option.equals("3")) {
                AddFlight.addFlight();
            } else if (option.equals("4")) {
                AddPassenger.addPassenger();
            } else if (option.equals("5")) {
                FlightStatus.flightStatus();
            } else if (option.equals("6")) {
                DeleteFlight.deleteFlight();
            } else if (option.equals("7")) {
                DeletePassanger.deletePassenger();
            } else if (option.equals("9")) {
                Login.closeProgram();
            } else {
                System.out.println("Invalid Menu input!");
                menu();
            }
        }else if(accessLevel <= 1){

        }
            System.out.println("\n" + "Select option:");
            System.out.println("1.Flight Info" + "\n" + "2.Passenger Info" + "\n" + "3.Flight status" + "\n" + "\n" + "9.Exit program");
            String option = sc.nextLine();

            if (option.equals("1")) {
                FlightInfo.flightInfo();
            } else if (option.equals("2")) {
                PassengersInfo.passengerInfo();
            } else if (option.equals("3")) {
                FlightStatus.flightStatus();
            } else if (option.equals("9")) {
                Login.closeProgram();
            } else {
                System.out.println("Invalid Menu input!");
                menu();
        }
    }
}
