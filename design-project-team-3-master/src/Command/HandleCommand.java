package Command;

import Database.DBEntry;
import Database.MusicLibrary;

import java.util.ArrayList;

// class to direct user input to correct commands
public class HandleCommand{

    private MusicLibrary currentLibrary;
    private MusicLibrary userLibrary;
    private MusicLibrary globalLibrary;
    private static String errorMessage = "Cannot execute command : ";
    private ArrayList<DBEntry> currentItems = new ArrayList<>();

    //Constructor for handle command takes a global and user library, default global as the current library;
    public HandleCommand(MusicLibrary globalLibrary, MusicLibrary userLibrary){
        this.globalLibrary = globalLibrary;
        this.currentLibrary = globalLibrary;
        this.userLibrary = userLibrary;
    }

    //Handles user commands
    public void handleCommand(String[] data){
        String[] newData = new String[2];
        switch (data[0]){
            case "help" :
                Help help = new Help();
                help.executeCommand(data, null);
                break;

            case "selectentry" :
                if (data.length < 2) {
                    System.out.println("Please specify which entry.");
                } else {
                    int index = Integer.parseInt(data[1]);
                    DBEntry selected = currentItems.get(index);
                    currentItems.clear();
                    currentItems.add(selected);
                }
                break;

            case "showitems" :
                if (data.length < 2 && currentItems.size() == 0) {
                    System.out.println("Please specify what to show. (Songs, artists, releases)");
                } else if (currentItems.size() != 0 && data.length < 2) {
                    int counter = 0;
                    for (DBEntry entry: currentItems) {
                        System.out.println(counter + ": " + entry.getName());
                    }
                } else {
                    currentItems.clear();
                    ShowItemsCommand showItems = new ShowItemsCommand();
                    currentItems = showItems.executeCommand(data, this.currentLibrary);
                }
                break;

            case "addentry" :
                AddCommand add = new AddCommand(this.globalLibrary);
                DBEntry entry = null;
                if (data.length > 1) {
                    int index = Integer.parseInt(data[1]);
                    if (index > currentItems.size() || index < 0) {
                        System.out.println("Index out of range.");
                    } else {
                        entry = currentItems.get(index);
                    }
                } else if (currentItems.size() == 1) {
                    entry = currentItems.get(0);
                }
                newData[0] = entry.getGUID();
                newData[1] = entry.getClass().toString();
                add.executeCommand(newData, userLibrary);
                break;

            case "removeentry" :
                RemoveCommand remove = new RemoveCommand();
                if (data.length < 2) {
                    if (currentItems.size() < 2) {
                        newData[0] = currentItems.get(0).getGUID();
                    } else {
                        System.out.println("Please select entry.");
                    }
                } else {
                    int index = Integer.parseInt(data[1]);
                    newData[0] = currentItems.get(index).getGUID();
                }
                remove.executeCommand(newData, userLibrary);
                break;

            case "ratesong" :
                RateSong rateSong = new RateSong();
                if(currentItems.size() > 0) {
                    String GUID = currentItems.get(0).getGUID();
                    data[0] = GUID;
                    if (userLibrary.getSongsTable().getByGUID(GUID) != null) {
                        rateSong.executeCommand(data, currentLibrary);
                    } else {
                        System.out.println("Cannot find song in your library, please add the song to you library first.");
                    }
                } else {
                    System.out.println("Please select a song to rate with the selectentry command.");
                }
                break;

            case "switchlibrary" :
                if (currentLibrary.equals(globalLibrary)){
                    currentLibrary = userLibrary;
                    System.out.println("Currrent library is now userLibrary");
                } else if (currentLibrary.equals(userLibrary)){
                    currentLibrary = globalLibrary;
                    System.out.println("Currrent library is now globalLibrary");
                }
                break;
            case "search" :
                SearchCommand searchCommand = new SearchCommand();
                this.currentItems = searchCommand.executeCommand(data, currentLibrary);
                break;
            case "save" :
                Save save = new Save();
                save.executeCommand(data, userLibrary);
                break;
            case "sort" :

                break;
            default :
                System.out.printf(errorMessage, data[0]);
        }
    }
}