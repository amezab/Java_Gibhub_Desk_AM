package com.medialist.command;
import com.medialist.service.MediaService;
import com.medialist.util.TerminalUtil;

public class RemoveMediaCommand {

    public void execute (MediaService mediaServiceVar, TerminalUtil terminalMediaVar) {

        if (mediaServiceVar.isEmpty()){//Checking if the list is empty
            terminalMediaVar.displayMessage("There is nothing to remove");
            return;

        }
        //In case list is empty and there is nothing to be removed
        String mediaNameToRemove = terminalMediaVar.getString("Enter name of media to remove: ");

        //Since this returns a boolean we store it and use it to check if media has been removed or not found
        mediaServiceVar.removeMedia(mediaNameToRemove);
        boolean mediaRemovedCompleted =  mediaServiceVar.removeMedia(mediaNameToRemove);
        if (mediaRemovedCompleted){
            terminalMediaVar.displayMessage("Media has been removed successfully!");
        }
        else {
            terminalMediaVar.displayMessage("ERROR: Media has not been found");
        }
    }


}
