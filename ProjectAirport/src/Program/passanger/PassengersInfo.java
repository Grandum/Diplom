package Program.passanger;

import Program.Flight.JDBCTutorialUtilities;
import Program.Login;
import Program.Menu;

import java.sql.*;

public class PassengersInfo {

    public static void passengerInfo() throws SQLException {
        if (Login.accessLevel >= 1) {

            Connection connection = null;
            try {
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:PassengerDB.db");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } finally {

            }
            Statement stmt = null;
            String query;
            query = "select Info.id, Info.FirstName, Info.LastName, Info.Passport, Info.Age, Citizen.Citizen, Info.Sex, Info.FlightNum from Info inner join Citizen on Info.Citizen = Citizen.id";

            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String passport = rs.getString("Passport");
                    int age = rs.getInt("Age");
                    String citizen = rs.getString("Citizen");
                    String sex = rs.getString("sex");
                    int flightNum = rs.getInt("FlightNum");
                    System.out.println("Passenger info:" + "\t" + id + "\t" + firstName + "\t" + lastName + "\t" + passport + "\t" + age + "\t" + citizen + "\t" + sex + "\t" + flightNum);
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
