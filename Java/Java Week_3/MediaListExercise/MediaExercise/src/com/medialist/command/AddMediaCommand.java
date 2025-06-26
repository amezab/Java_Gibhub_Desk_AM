package com.medialist.command;
import com.medialist.model.Media;
import com.medialist.model.Video;
import com.medialist.model.Audio;
import com.medialist.model.Image;
import com.medialist.model.Book;
import com.medialist.service.MediaService;
import com.medialist.util.TerminalUtil;

public class AddMediaCommand {
    public void execute(MediaService mediaServiceVar, TerminalUtil terminalMediaVar) {
        terminalMediaVar.displayMessage("Choose a type of media: ");//Menu options
        terminalMediaVar.displayMessage("1. Video");
        terminalMediaVar.displayMessage("2. Audio");
        terminalMediaVar.displayMessage("3. Image");
        terminalMediaVar.displayMessage("4. Book");
        int mediaTypeChoice;//storing users choice

        //Validating users choice
        do {
            mediaTypeChoice = terminalMediaVar.getInt("Choose and option: 1-4");
            if (mediaTypeChoice > 4 || mediaTypeChoice < 1 ){
                terminalMediaVar.displayMessage("Invalid option. Please choose a number between 1 and 4.");
            }
        } while (mediaTypeChoice > 4 || mediaTypeChoice < 1 );


        //This will hold the media object(video, audio, book or image
        Media newMedia = null;

        switch (mediaTypeChoice) {
            case 1:
                terminalMediaVar.displayMessage("===Add Video===");
                String videoName = terminalMediaVar.getString("Enter video name: ");
                int videoMinutes = terminalMediaVar.getInt("Enter minutes per video: ");
                String videoResolution = terminalMediaVar.getString("Enter resolution video (ej. 1080p, 4k...: ");
                newMedia = new Video(videoName, videoMinutes, videoResolution);//it creates a new instance of a video using the information collected from the user.
                break;
            case 2:
                terminalMediaVar.displayMessage("===Add Audio===");
                String audioName = terminalMediaVar.getString("Enter audio name: ");
                int audioMinutes = terminalMediaVar.getInt("Enter minutes per audio: ");
                String artisName = terminalMediaVar.getString("Enter Artist's name: ");
                newMedia = new Audio(audioName, audioMinutes, artisName);
                break;
            case 3:
                terminalMediaVar.displayMessage("===Add Image===");
                String imageName = terminalMediaVar.getString("Enter image name: ");
                String imageSize = terminalMediaVar.getString("Enter image dimensions: ");
                String fileType = terminalMediaVar.getString("Enter file type: ");
                newMedia = new Image(imageName, imageSize, fileType);
                break;
            case 4:
                terminalMediaVar.displayMessage("===Add Book===");
                String bookName = terminalMediaVar.getString("Enter book's name: ");
                String authorName = terminalMediaVar.getString("Enter Author's name: ");
                int pageCount = terminalMediaVar.getInt("Enter amount of pages: ");
                newMedia = new Book(bookName, authorName, pageCount);
                break;


        }
        mediaServiceVar.addMedia(newMedia);
        terminalMediaVar.displayMessage("Media has been added");


        }

    }


