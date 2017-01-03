package Program.Flight;

import Program.Menu;

import java.sql.*;
import java.util.Scanner;

public class FlightStatus {

        public static void flightStatus() throws SQLException {

            Scanner sc = new Scanner(System.in);

            System.out.println("Flight id (int):");
            String ID = sc.nextLine();

            Connection connection = null;
            try {
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:FlightDB.db");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }finally {

            }
            Statement stmt = null;
            String query3;
            query3 = "select FlightInfo.Airline, FlightInfo.FlightNumber, FlightInfo.Direction, FlightSchedule.FlightDate, FlightSchedule.Departure, FlightSchedule.Arrival, FlightStatus.StatusID " +
                     "from FlightInfo inner join FlightSchedule on FlightInfo.FlightSchedule = FlightSchedule.id inner join FlightClass on FlightInfo.FlightClass = FlightClass.id inner join FlightStatus on FlightInfo.FlightStatus = FlightStatus.id " +
                     "where FlightInfo.id = '" + ID + "'";

            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query3);
                while (rs.next()){
                    int flightNum = rs.getInt("FlightNumber");
                    String airlines = rs.getString("Airline");
                    String direction = rs.getString("Direction");
                    String flightStatus = rs.getString("StatusID");
                    String flightDate = rs.getString("FlightDate");
                    String departure = rs.getString("Departure");
                    String arrival = rs.getString("Arrival");
                    System.out.println("Flight Status:" + "\t" + flightNum + "\t" + airlines + "\t" + direction + "\t" + flightDate + "\t" + departure + "\t" + arrival + "\t" + flightStatus);
                }
            } catch (SQLException e) {
                JDBCTutorialUtilities.printSQLException(e);
            } finally {
                if (stmt != null) { stmt.close();}

            }

            {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Menu.menu();


        }

    }
