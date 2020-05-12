package Command;

import java.io.IOException;
import java.util.ArrayList;

import Database.*;
import UserTier.*;

public class Save implements MusicCommand{

    //Exports the user data to csv so the user can terminate the program and have their data stored.
    public ArrayList<DBEntry> executeCommand(String[] data, MusicLibrary library){

            ExportUserCSV exportUserCSV = new ExportUserCSV();

            User user = ((UserLibrary) library).getUser();

            try { 
                exportUserCSV.exportToFiles(user);
                System.out.println("Save succesful!");
            } catch (IOException e){
                e.printStackTrace();
            }

        return null;
    }
}