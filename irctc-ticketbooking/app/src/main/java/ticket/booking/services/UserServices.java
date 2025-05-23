package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserServices {

    private User user;
    private static final String USERS_PATH = "../db/users.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private List<User> listOfusers;


    public UserServices(User user) throws IOException{
        this.user = user;
        loadUsers();

    }
    public UserServices() throws IOException {
        loadUsers();
    }
    public void loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        listOfusers = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean UserLogin(){
        Optional<User> foundUser = listOfusers.stream().filter(user1->{
            return (user1.getName()).equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();

    }
    public void UserLogout(User user){}

    public Boolean signup(User user){
        try{
            listOfusers.add(user);
            saveUsertoFile();
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public void saveUsertoFile() throws IOException {
        try{
            File users = new File(USERS_PATH);
            objectMapper.writeValue(users, listOfusers);
        }catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Ticket> fetchBookings(){
        return user.getTicketsBooked();
    }

    public Boolean cancelTicket(){
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the id of the ticket you wish to cancel: ");
        String ticketId = s.next();
        List<Ticket> tickets = fetchBookings();

        boolean removed = tickets.removeIf(t ->
             t.getTicketId().equals(ticketId)
        );

        if(removed){
            System.out.println("Ticket has been cancelled");
            return true;
        }else{
            System.out.println("Ticket has not been cancelled");
            return false;
        }

    }
    public List<Train> getTrains(String source, String destination){
        try{
            TrainServices trainService = new TrainServices();
            return trainService.searchTrains(source, destination);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainServices trainService = new TrainServices();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }



}
