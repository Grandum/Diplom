package Program.Flight;

import Program.Login;
import Program.Menu;

import java.sql.*;
import java.util.Scanner;

public class AddFlight {
    public static void addFlight() throws SQLException {
        if (Login.accessLevel >= 2) {

            Scanner sc = new Scanner(System.in);

            System.out.println("Flight id (int):");
            String ID = sc.nextLine();
            System.out.println("Airline (Str):");
            String Airline = sc.nextLine();
            System.out.println("Flight Number (int):");
            String FlightNum = sc.nextLine();
            System.out.println("Direction (Str):");
            String Direction = sc.nextLine();
            System.out.println("Flight Class (int):");
            String FlightClass = sc.nextLine();
            System.out.println("Price (int):");
            String Price = sc.nextLine();
            System.out.println("Number of Passengers (int):");
            String Passengers = sc.nextLine();
            System.out.println("Flight Schedule (int):");
            String Schedule = sc.nextLine();
            System.out.println("Status (1-7):");
            String Status = sc.nextLine();
            System.out.println("Gate (int):");
            String Gate = sc.nextLine();
            System.out.println("Schedule id (int):");
            String ScheduleID = sc.nextLine();
            System.out.println("Flight date (dd.mm.yyyy):");
            String Date = sc.nextLine();
            System.out.println("Registration start (hh:mm):");
            String RegStrat = sc.nextLine();
            System.out.println("Registration end (hh:mm):");
            String RegEnd = sc.nextLine();
            System.out.println("Board start (hh:mm):");
            String Board = sc.nextLine();
            System.out.println("Departure (hh:mm):");
            String Departure = sc.nextLine();
            System.out.println("Arrival (hh:mm):");
            String Arrival = sc.nextLine();

            Connection connection = null;
            try {
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:FlightDB.db");
//                System.out.println("Connection OK!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } finally {

            }
            Statement stmt = null;
            String queryAddInfo = "INSERT into FlightInfo VALUES ('" + ID + "', '" + Airline + "', '" + FlightNum + "', '" + Direction + "', '" + FlightClass + "', '" + Price + "', '" + Passengers + "', '" + Schedule + "', '" + Status + "', '" + Gate + "' )";
            String queryAddSchedule = "INSERT into FlightSchedule VALUES ('" + ScheduleID + "', '" + Date + "', '" + RegStrat + "', '" + RegEnd + "', '" + Board + "', '" + Departure + "', '" + Arrival + "');";
            String queryCheckInfo = "select FlightInfo.id, FlightInfo.Airline, FlightInfo.FlightNumber, FlightInfo.Direction, FlightClass.Class, FlightInfo.Price, FlightSchedule.FlightDate, FlightSchedule.RegisterStart, FlightSchedule.BoardStart, FlightSchedule.Departure, FlightSchedule.Arrival" + " from FlightInfo inner join FlightSchedule on FlightInfo.FlightSchedule = FlightSchedule.id inner join FlightClass on FlightInfo.FlightClass = FlightClass.id";

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                stmt = connection.createStatement();
                ResultSet rs1 = stmt.executeQuery(queryAddInfo);
                while (rs1.next()) {
                    System.out.println("Added successfully:");
                }

            } catch (SQLException e) {
                JDBCTutorialUtilities.printSQLException(e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                stmt = connection.createStatement();
                ResultSet rs2 = stmt.executeQuery(queryAddSchedule);
                while (rs2.next()) {
                    System.out.println("Added successfully");
                }
            } catch (SQLException e) {
                JDBCTutorialUtilities.printSQLException(e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }

            }

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                stmt = connection.createStatement();
                ResultSet rs3 = stmt.executeQuery(queryCheckInfo);
                while (rs3.next()) {
                    int id = rs3.getInt("id");
                    int flightNum = rs3.getInt("FlightNumber");
                    String airlines = rs3.getString("Airline");
                    String direction = rs3.getString("Direction");
                    String flightClass = rs3.getString("Class");
                    String flightDate = rs3.getString("FlightDate");
                    String boardStart = rs3.getString("BoardStart");
                    String regStart = rs3.getString("RegisterStart");
                    String departure = rs3.getString("Departure");
                    String arrival = rs3.getString("Arrival");
                    int price = rs3.getInt("Price");
                    System.out.println("Flight info:" + "\t" + id + "\t" + flightNum + "\t" + "\t" + airlines + "\t" + direction + "\t" + flightClass + "\t" + flightDate + "\t" + regStart + "\t" + boardStart + "\t" + departure + "\t" + arrival + "\t" + "\t" + price);
                }
            } catch (SQLException e) {
                JDBCTutorialUtilities.printSQLException(e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }

            }

            {
                try {
                    if (connection != null) connection.close();
//                System.out.println("Connection closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Menu.menu();


        } else {
            System.out.println("Not enough Access Level");
        }

    }
}
