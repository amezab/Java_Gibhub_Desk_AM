package com.medialist.app;
import com.medialist.service.MediaService;
import com.medialist.util.TerminalUtil;
import com.medialist.command.AddMediaCommand;
import com.medialist.command.RemoveMediaCommand;
import com.medialist.command.PlayMediaCommand;
import com.medialist.command.ListAllMediaCommand;

public class Main {
    public static void main(String[] args) {

        MediaService mediaService =new MediaService();
        TerminalUtil terminalUtil = new TerminalUtil();

        //To control if app still running and to save users choice
        boolean running = true;
        int choice;

        //What do I want to repeat?
        do {
            terminalUtil.displayMenu();//Showing the menu options
            choice = terminalUtil.getMenuChoice();//validates the users choice
            switch (choice){
                case 1:
                    AddMediaCommand addMediaCommand = new AddMediaCommand();
                    addMediaCommand.execute(mediaService,terminalUtil);//Calling execute to add
                    break;
                case 2:
                    RemoveMediaCommand removeMediaCommand = new RemoveMediaCommand();
                    removeMediaCommand.execute(mediaService,terminalUtil);//to remove
                    break;
                case 3:
                    PlayMediaCommand playMediaCommand = new PlayMediaCommand();
                    playMediaCommand.execute(mediaService,terminalUtil);//to play
                    break;
                case 4:
                    ListAllMediaCommand listAllMediaCommand = new ListAllMediaCommand();
                    listAllMediaCommand.execute(mediaService, terminalUtil);//to show the list
                    break;
                case 5:
                    terminalUtil.displayMessage("Bye! Thanks for using the App");
                    running = false;
                    break;
                default:
                terminalUtil.displayMessage("That's not a valid option, please select from 1-5");

            }

        }while (running);


    }

}