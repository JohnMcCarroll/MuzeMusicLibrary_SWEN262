package Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import Database.MusicLibrary;

// STDInputParser
// Reads Standard input and parses it into data that the 
// command subsystem can use to execute on the rest of the system
public class STDInputParser {

    HandleCommand commandHandler;

    /**
     * Creates a command handler with a globalLibrary and a userLibrary
     */
    public STDInputParser(MusicLibrary globalLibrary, MusicLibrary userLibrary){
        this.commandHandler = new HandleCommand(globalLibrary, userLibrary);

    }

    HashSet<String> commandList = new HashSet<String>(Arrays.asList("selectentry", "addentry", "removeentry",
            "ratesong", "switchlibrary", "search", "help", "showitems", "save"));

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Parses through one user input and determines if it is a potential valid command, if so it passes it on to
     * handlecommand.
     */
    public String[] parseSTDIn() throws IOException{
 
        System.out.println("\n\renter command : ");

        String data = reader.readLine();

        String lowerCaseData = data.toLowerCase();
        
        String[] dataList = lowerCaseData.split(" ");

        if (commandList.contains(dataList[0])){
            commandHandler.handleCommand(dataList);
        } else {
            System.out.println("Invalid command, for a list of available commands type 'help'.");
        }

        return dataList;
    }
}