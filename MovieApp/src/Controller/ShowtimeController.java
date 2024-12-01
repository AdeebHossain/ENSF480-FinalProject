package Controller;

import java.sql.*;

public class ShowtimeController {
    private  MySQLConnection connection;

    // Constructor to initialize the database connection
    public ShowtimeController() {
        connection = MySQLConnection.getInstance();
    }

    public String[] getShowtimeInfo(int id){
        String query = "SELECT * FROM showtimes WHERE movie_id = ?";
        ResultSet results = connection.query(query, id);

        String temp = "";
        try{
            while (results.next()){
                temp = temp + results.getString("showtime") + " ";
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        String[] showtimeInfo = temp.split(" ");
        return showtimeInfo;
    }
    
}