package Program.Flight;

import Program.Login;
import Program.Menu;

import java.sql.*;

public class FlightInfo {


    public static void flightInfo() throws SQLException {

        if (Login.accessLevel >= 1) {

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
            String query2;
            query2 = "select FlightInfo.id, FlightInfo.Airline, FlightInfo.FlightNumber, FlightInfo.Direction, FlightClass.Class, FlightInfo.Price, FlightSchedule.FlightDate, FlightSchedule.RegisterStart, FlightSchedule.BoardStart, FlightSchedule.Departure, FlightSchedule.Arrival" + " from FlightInfo inner join FlightSchedule on FlightInfo.FlightSchedule = FlightSchedule.id inner join FlightClass on FlightInfo.FlightClass = FlightClass.id ";

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query2);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int flightNum = rs.getInt("FlightNumber");
                    String airlines = rs.getString("Airline");
                    String direction = rs.getString("Direction");
                    String flightClass = rs.getString("Class");
                    String flightDate = rs.getString("FlightDate");
                    String boardStart = rs.getString("BoardStart");
                    String regStart = rs.getString("RegisterStart");
                    String departure = rs.getString("Departure");
                    String arrival = rs.getString("Arrival");
                    int price = rs.getInt("Price");
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
