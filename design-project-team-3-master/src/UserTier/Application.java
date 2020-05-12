package UserTier;

import java.util.HashMap;
//import Database.

// class to handle user login
public class Application{


    private User currentUser;
    private HashMap<String, String> userInfo;

    public Application(HashMap<String, String> userInfo){
        this.userInfo = userInfo;
    }

}