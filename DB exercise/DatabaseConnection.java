import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sankari on 12.11.2015.
 */
public class DatabaseConnection {

    //  You must download the mysql-connector-java.jar from MySQL
    //  homepage in order to use this driver!

    private String driverName = "com.mysql.jdbc.Driver";

    private String url =
            "jdbc:mysql://mydb.tamk.fi/"
                    + "XXX"                   // Your database
                    + "?user=XXX"             // Your login name (foo)
                    + "&password=XXX"         // Your mysql pw (bar)
            ;

    
    // Test method to demonstrate JDBC SQL SELECT statement.
    public void testSelect() {

        try {

            // Loads the driver
            Class.forName(driverName);

            // Gets a connection to database
            Connection con = DriverManager.getConnection(url);

            // Creates statement and executes update
            String    sql    = ""
                    + "SELECT    * "
                    + "FROM      Highscore "
                    + "ORDER BY  BestScore DESC";

            Statement stmt   = con.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            // Goes through "name" column and prints output
            while ( result.next() ) {
                String ename  = result.getString("Name");
                int score = result.getInt("BestScore");
                System.out.println(ename + " " + score);
            }

            // Closes all the connections.
            result.close();
            stmt.close();
            con.close();
        } catch (Exception e) {  // If something went wrong.
            System.err.println("Exception: " + e.getMessage() );
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void updatePlayerInfo(String name, int score) {
        // Loads the driver
        try {
            Class.forName(driverName);

            // Gets a connection to database
            Connection con = DriverManager.getConnection(url);

            // Creates statement and executes update
            String    sql    = ""
                    + "INSERT INTO Highscore "
                    + "VALUES ('" + name + "', " + score + ")";

            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate(sql);

            // Closes all the connections.
            stmt.close();
            con.close();

        // Catches all the exceptions.
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage() );
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void updateMultiInfo(String name, int wins, int losses) {
        
        try {
            // Loads the driver
            Class.forName(driverName);

            // Gets a connection to database
            Connection con = DriverManager.getConnection(url);

            // A list for existing names in database
            ArrayList<String> nameArray = new ArrayList<>();
            String existingNames = "" 
                    + "SELECT    Name "
                    + "FROM      Multiplayer_scores";
            Statement stmt1   = con.createStatement();
            ResultSet result1 = stmt1.executeQuery(existingNames);
            
            int a = 0;
            // Adds name in table to an array
            while ( result1.next() ) {
                String eXname  = result1.getString("Name");
                nameArray.add(eXname);
                a++;
            }
          
            // Checks if the name is already in the database
            Statement stmt3 = con.createStatement();
            Statement stmt2 = con.createStatement();
            for (int b = 0; b < nameArray.size(); b++) {
                if (name.equals(nameArray.get(b))) {
                    String getScores = "" 
                        + "SELECT    * "
                        + "FROM      Multiplayer_scores "
                        + "WHERE     Name = '" + name + "'";
                    stmt3 = con.createStatement();
                    ResultSet result3 = stmt3.executeQuery(getScores);
                    // Adds existing scores to last game's scores
					while ( result3.next() ) {
						wins += result3.getInt("Wins");
						losses += result3.getInt("Losses");
					}
                    
                    // Deletes the row from the table
                    String delete = ""
                        + "DELETE FROM Multiplayer_scores "
                        + "Where Name = '" + name + "'";
                    stmt2 = con.createStatement();
                    int result2 = stmt2.executeUpdate(delete);
                }
            }

            // Creates statement and executes update
            String    sql    = ""
                    + "INSERT INTO Multiplayer_scores "
                    + "VALUES ('" + name + "', " + losses + ", " + wins + ")";

            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate(sql);

            // Closes all the connections.
            stmt3.close();
            stmt2.close();
            stmt.close();
            con.close();
            
        // Catches all the exceptions.
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage() );
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void selectMulti() {
        try {

            // Loads the driver
            Class.forName(driverName);

            // Gets a connection to database
            Connection con = DriverManager.getConnection(url);

            // Creates statement and executes update
            String    sql    = ""
                    + "SELECT    * "
                    + "FROM      Multiplayer_scores "
                    + "ORDER BY  Wins DESC";

            Statement stmt   = con.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            // Goes through "name, wins and losses" column and prints output
            while ( result.next() ) {
                String ename  = result.getString("Name");
                int win = result.getInt("Wins");
                int lost = result.getInt("Losses");
                System.out.println(ename + ": wins " + win + ", losses " + lost);
            }

            // Closes all the connections.
            result.close();
            stmt.close();
            con.close();
        }
        catch (Exception e) {  // If something went wrong.
            System.err.println("Exception: " + e.getMessage() );
            e.printStackTrace();
            System.exit(1);
        }
    }
}
