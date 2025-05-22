package ticket.booking.utils;


import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {

    public static Boolean checkPassword(String password, String hashedPassword){

        return BCrypt.checkpw(password,hashedPassword);
    }
}
