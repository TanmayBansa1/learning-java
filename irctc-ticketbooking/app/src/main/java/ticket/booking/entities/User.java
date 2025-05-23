package ticket.booking.entities;

import java.util.List;

public class User {
    private String name;
    private String password;
    private String hashPassword;
    private String userId;
    private List<Ticket> ticketsBooked;

    public User(){};
    public User(String name, String password, String hashPassword, String userId, List<Ticket> ticketsBooked ){
        this.name = name;
        this.password = password;
        this.hashPassword = hashPassword;
        this.userId = userId;
        this.ticketsBooked = ticketsBooked;
    }



    public String getName(){
        return name;
    }
    public String getHashedPassword(){
        return hashPassword;
    }
    public String getUserId(){
        return userId;
    }
    public List<Ticket> getTicketsBooked(){
        return ticketsBooked;
    }
    public String getPassword(){
        return password;
    }


    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public void setTicketsBooked(List<Ticket> ticketsBooked){
        this.ticketsBooked = ticketsBooked;
    }
    public void setHashPassword(String hashPassword){
        this.hashPassword = hashPassword;
    }

    public void printTicketsBooked(){
        ticketsBooked.forEach(System.out::println);
    }
}
