package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnection {
    
        private Connection connector;
        static MySQLConnection instance;

        // Define database credentials
        String url = "jdbc:mysql://localhost:3306/movietheatredb"; 
        String username = "root";
        String password = "P@$$w0rd123";
    
        private MySQLConnection() {
            try {
                // Load the MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Create the connection
                this.connector = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        //Get instance method to make sure DBController is singleton
        public static MySQLConnection getInstance() { 
            if (instance == null){
                instance = new MySQLConnection();
            }
            return instance;
        }

         //Main Query Method --> Returns a ResultSet object
        public ResultSet query(String query, Object... args){
            try{
                PreparedStatement statement = connector.prepareStatement(query);

                for (int i = 0; i < args.length; i++){
                    statement.setObject(i + 1, args[i]);
                }

                ResultSet results = statement.executeQuery();
                return results;
            } catch(SQLException e){
                e.printStackTrace();
            }

            return null;
        }

        //Main Execute Method --> Executes queries and updates the DB
        public void execute(String query, Object... args){
            try {
                PreparedStatement statement = connector.prepareStatement(query);
                for (int i = 0; i < args.length; i++){
                    statement.setObject(i + 1, args[i]);
                }

                statement.execute();

            } catch (SQLException e){
                e.printStackTrace();
            }
        }

}
