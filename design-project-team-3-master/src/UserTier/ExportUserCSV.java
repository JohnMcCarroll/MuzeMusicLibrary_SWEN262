package UserTier;

import Database.UserLibrary;

import java.io.*;
import java.util.ArrayList;

public class ExportUserCSV {

    /**
     * Exports the user library to csv files to save the user data, and be able to reload it when the system restarts.
     */
    public boolean exportToFiles(User user) throws IOException {
        File userDir = new File("data\\user");
        userDir.mkdir();
        String username = user.getUsername();
        String filename = username + "_songs.csv";
        File file = new File("data\\user\\" + filename);
        UserLibrary library = user.getLibrary();
        ArrayList<ArrayList<String>> list = library.getGuidList();
        boolean deleted0 = file.delete();
        boolean created0 = file.createNewFile();
        System.out.println("Saving songs");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String entry : list.get(0)) {
            writer.write(entry);
            writer.newLine();
        }
        writer.flush();

        filename = username + "_artists.csv";
        file = new File("data\\User\\" + filename);
        boolean deleted1 = file.delete();
        boolean created1 = file.createNewFile();
        System.out.println("Saving artists");
        writer = new BufferedWriter(new FileWriter(file));
        for (String entry : list.get(1)) {
            writer.write(entry);
            writer.newLine();
        }
        writer.flush();
        filename = username + "_releases.csv";
        file = new File("data\\User\\" + filename);
        boolean deleted2 = file.delete();
        boolean created2 = file.createNewFile();
        System.out.println("saving releases");
        writer = new BufferedWriter(new FileWriter(file));
        for (String entry : list.get(2)) {
            writer.write(entry);
            writer.newLine();
        }
        writer.flush();
        return created0 && created1 && created2;
    }
}
