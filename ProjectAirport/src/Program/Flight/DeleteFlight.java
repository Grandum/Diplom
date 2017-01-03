package Program.Flight;

import Program.Login;
import Program.Menu;

import java.sql.*;
import java.util.Scanner;

public class DeleteFlight {
    public static void deleteFlight() throws SQLException {
        if (Login.accessLevel >= 2) {

            Scanner sc = new Scanner(System.in);

            System.out.println("Flight id (int):");
            String FlightID = sc.nextLine();
            System.out.println("Schedule id (int):");
            String ScheduleID = sc.nextLine();

            Connection connection = null;
            try {
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                    }
                    connection = DriverManager.getConnection("jdbc:sqlite:FlightDB.db");
//                System.out.println("Connection OK!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } finally {

            }
            Statement stmt = null;
            String queryDelInfo;
            String queryDelSchedule;
            String queryCheckInfo;
            queryDelInfo = "delete from FlightInfo where id = '" + FlightID + "'";
            queryDelSchedule = "delete from FlightSchedule where id = '" + ScheduleID + "'";
            queryCheckInfo = "select FlightInfo.Airline, FlightInfo.FlightNumber, FlightInfo.Direction, FlightClass.Class, FlightInfo.Price, FlightSchedule.FlightDate, FlightSchedule.RegisterStart, FlightSchedule.BoardStart, FlightSchedule.Departure, FlightSchedule.Arrival from FlightInfo inner join FlightSchedule on FlightInfo.FlightSchedule = FlightSchedule.id inner join FlightClass on FlightInfo.FlightClass = FlightClass.id";

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                stmt = connection.createStatement();
                ResultSet rs1 = stmt.executeQuery(queryDelInfo);
                while (rs1.wasNull()) {
                    System.out.println("Deleted successfully:");
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
                ResultSet rs2 = stmt.executeQuery(queryDelSchedule);
                while (rs2.next()) {
                    System.out.println("Delete successfully");
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
                    System.out.println("Flight info:" + "\t" + flightNum + "\t" + "\t" + airlines + "\t" + direction + "\t" + flightClass + "\t" + flightDate + "\t" + regStart + "\t" + boardStart + "\t" + departure + "\t" + arrival + "\t" + "\t" + price);
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
