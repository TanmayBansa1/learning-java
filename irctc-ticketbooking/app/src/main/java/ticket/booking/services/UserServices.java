package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserServices {

    private User user;
    private static final String USERS_PATH = "../db/users.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private List<User> listOfusers;
    public void UserServiceInitiator(User user) throws IOException{
        this.user = user;
        File users = new File(USERS_PATH);
        listOfusers = objectMapper.readValue(users, new TypeReference<List<User>>() {});

    }

    public Boolean UserLogin(){
        Optional<User> foundUser = listOfusers.stream().filter(user1->{
            return (user1.getName()).equals(user.getName()) && UserServiceUtil.checkPassword(user1.getPassword(),user.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();

    }
    public void UserLogout(User user){}

}
