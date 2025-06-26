package com.medialist.command;
import java.util.List;
import com.medialist.model.Media;
import com.medialist.service.MediaService;
import com.medialist.util.TerminalUtil;

public class ListAllMediaCommand {
    public void execute(MediaService mediaServiceVar, TerminalUtil terminalMediaVar){
        if (mediaServiceVar.isEmpty()){//Checking if the list is empty
            terminalMediaVar.displayMessage("List is empty");
            return;

        }
        //Copy of everything from the media library
        List<Media>allMediaList = mediaServiceVar.getAllMedia();
        //Shows whats actually on the library
        terminalMediaVar.displayMediaList(allMediaList);




    }
}
