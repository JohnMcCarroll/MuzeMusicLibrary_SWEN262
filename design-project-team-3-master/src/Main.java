import Command.STDInputParser;
import Database.*;
import UserTier.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        LibraryMaker lbm = new LibraryMaker();
        GlobalLibrary gbl = lbm.createGlobalLibrary();
        System.out.println("gbl made" + gbl);
        UserLibrary usl = lbm.createUserLibrary();

        User user = new User("USER", usl);
        usl.user = user;

        System.out.println("usl made" + usl);
        STDInputParser yes = new STDInputParser(gbl, usl);
        while (true) {
            try {
                String[] data = yes.parseSTDIn();
            } catch (IOException ie) {
                System.out.println("error");
            }
        }
        
    }
}
