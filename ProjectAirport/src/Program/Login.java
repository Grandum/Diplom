package Program;

import java.sql.*;
import java.util.Scanner;

public class Login {
    public static int accessLevel;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Login:");
        String login = sc.nextLine();
        System.out.println("Password:");
        String password = sc.nextLine();

        Connection connection = null;
        try {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:UsersDB.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            Statement stmt = null;
            String query;
            query = "SELECT Login, Password, AccessLevel FROM User where Login = '" + login + "'";
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String dbLogin = rs.getString("Login");
                    String dbPass = rs.getString("Password");
                    int dbAccess = rs.getInt("accessLevel");
                    if (password.equals(dbPass)) {
                        accessLevel = (dbAccess);
                        System.out.println("Access Granted for" + "\t" + dbLogin);
                        System.out.println("Access Level:" + accessLevel);
                    } else {
                        System.out.println("Invalid Password");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
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
        }

        if (accessLevel != 0){
            Menu.menu();
        }
    }
    public static void closeProgram(){
        System.exit(0);
    }
}
