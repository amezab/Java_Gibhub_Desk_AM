package com.medialist.command;
import com.medialist.model.Media;
import com.medialist.service.MediaService;
import com.medialist.util.TerminalUtil;

public class PlayMediaCommand {
    public void execute(MediaService mediaServiceVar, TerminalUtil terminalMediaVar) {
        //in case list is empty just like removeMedia
        if (mediaServiceVar.isEmpty()){
            terminalMediaVar.displayMessage("There is nothing in the library to be played");
            return;
        }

        terminalMediaVar.getString("What do you want to play? ");
        String mediaNameToPlay =  terminalMediaVar.getString("What do you want to play? ");

        Media mediaFound = mediaServiceVar.findMediaByName(mediaNameToPlay);
        if (mediaFound != null){
            mediaFound.play();

        }else {
            terminalMediaVar.displayMessage("ERROR: Media has not been found");
        }





    }

}



