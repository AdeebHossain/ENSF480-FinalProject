package Controller;

import Entities.Ticket;

public class TicketController {
    
    private MySQLConnection connection;
    private Ticket ticket;

    //Default Constructor
    public TicketController(){
        connection = MySQLConnection.getInstance();
    }

     //Constructor
     public TicketController(Ticket ticket){
        connection = MySQLConnection.getInstance();
        this.ticket = ticket;
    }

    //Method to add a ticket to the database
    
}
