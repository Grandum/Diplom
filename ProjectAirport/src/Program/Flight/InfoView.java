package Program.Flight;

import Program.Login;
import Program.Menu;

import java.sql.*;
import java.util.Scanner;

public class InfoView {

    public static void infoView() throws SQLException {

        if (Login.accessLevel >= 1) {

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
            } finally {

            }
            Statement stmt = null;
            String query4;
            query4 = "select FlightInfo.Airline, FlightInfo.FlightNumber, FlightInfo.Direction, FlightClass.Class, FlightSchedule.FlightDate, FlightSchedule.RegisterStart, FlightSchedule.BoardStart, FlightSchedule.Departure, FlightSchedule.Arrival, FlightInfo.Gate " +
                     "from FlightInfo inner join FlightSchedule on FlightInfo.FlightSchedule = FlightSchedule.id inner join FlightClass on FlightInfo.FlightClass = FlightClass.id " +
                     "where FlightInfo.id = '" + ID + "'";

            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query4);
                while (rs.next()) {
                    int flightNum = rs.getInt("FlightNumber");
                    String airlines = rs.getString("Airline");
                    String direction = rs.getString("Direction");
                    String flightClass = rs.getString("Class");
                    String flightDate = rs.getString("FlightDate");
                    String boardStart = rs.getString("BoardStart");
                    String regStart = rs.getString("RegisterStart");
                    String departure = rs.getString("Departure");
                    String arrival = rs.getString("Arrival");
                    int gate = rs.getInt("Gate");
                    System.out.println("Info View:" + "\t" + flightNum + "\t" + "\t" + airlines + "\t" + direction + "\t" + flightClass + "\t" + "\t" + flightDate + "\t" + regStart + "\t" + boardStart + "\t" + departure + "\t" + arrival + "\t" + gate);
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

