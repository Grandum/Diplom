package Program.passanger;

import Program.Flight.JDBCTutorialUtilities;
import Program.Login;
import Program.Menu;

import java.sql.*;
import java.util.Scanner;

public class DeletePassanger {
    public static void deletePassenger() throws SQLException {
        if (Login.accessLevel >= 2) {

            Scanner sc = new Scanner(System.in);

            System.out.println("Passenger id (int):");
            String Passenger = sc.nextLine();

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
            String addPass = "delete from Info where id = '" + Passenger + "';";
            String checkPass = "select Info.id, Info.FirstName, Info.LastName, Info.Passport, Info.Age, Citizen.Citizen, Info.Sex, Info.FlightNum from Info inner join Citizen on Info.Citizen = Citizen.id";

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                stmt = connection.createStatement();
                ResultSet rs1 = stmt.executeQuery(addPass);
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
                stmt = connection.createStatement();
                ResultSet rs2 = stmt.executeQuery(checkPass);
                while (rs2.next()) {
                    int id = rs2.getInt("id");
                    String firstName = rs2.getString("FirstName");
                    String lastName = rs2.getString("LastName");
                    String passport = rs2.getString("Passport");
                    int age = rs2.getInt("Age");
                    String citizen = rs2.getString("Citizen");
                    String sex = rs2.getString("sex");
                    int flightNum = rs2.getInt("FlightNum");
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
