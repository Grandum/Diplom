package Program.Flight;

import Program.Login;
import Program.Menu;

import java.sql.*;

public class UpdateFlight {
    public static void updateFlight() throws SQLException {
        if (Login.accessLevel >= 2) {

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
            String queryUpdateInfo = "update FlightInfo set FlightStatus = 7 where id = 4";
            String queryUpdateSchedule = "update  FlightSchedule set Departure = '14:55', Arrival = '21:30' where id = 4";
            String queryCheckInfo = "select FlightInfo.id, FlightInfo.Airline, FlightInfo.FlightNumber, FlightInfo.Direction, FlightClass.Class, FlightInfo.Price, FlightSchedule.FlightDate, FlightSchedule.RegisterStart, FlightSchedule.BoardStart, FlightSchedule.Departure, FlightSchedule.Arrival, FlightInfo.Gate" + " from FlightInfo inner join FlightSchedule on FlightInfo.FlightSchedule = FlightSchedule.id inner join FlightClass on FlightInfo.FlightClass = FlightClass.id";

            if (connection != null) {
                connection.setAutoCommit(false);
            }

            try {
                stmt = connection.createStatement();
                ResultSet rs1 = stmt.executeQuery(queryUpdateInfo);
                while (rs1.next()) {
                    System.out.println("Updated successfully:");
                }

            } catch (SQLException e) {
                JDBCTutorialUtilities.printSQLException(e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }

            }

            try {
                stmt = connection.createStatement();
                ResultSet rs1 = stmt.executeQuery(queryUpdateSchedule);
                while (rs1.next()) {
                    System.out.println("Updated successfully:");
                }

            } catch (SQLException e) {
                JDBCTutorialUtilities.printSQLException(e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }

            }

            try {
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
                    int gate = rs3.getInt("Gate");
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
                    connection.commit();
                    connection.close();
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
