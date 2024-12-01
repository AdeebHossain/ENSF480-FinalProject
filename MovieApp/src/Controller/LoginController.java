package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    private MySQLConnection connection;

    //Constructor 
    public LoginController() {
        this.connection = MySQLConnection.getInstance();
    }

}
