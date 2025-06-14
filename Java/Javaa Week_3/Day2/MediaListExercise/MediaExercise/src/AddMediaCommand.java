import static javax.swing.UIManager.getInt;

public class AddMediaCommand {
    public int execute(MediaService mediaServiceVar, TerminalUtil terminalMediaVar) {
        terminalMediaVar.displayMessage("Selecciona un tipo de medio: ");//Menu options
        terminalMediaVar.displayMessage("1. Video");
        terminalMediaVar.displayMessage("2. Audio");
        terminalMediaVar.displayMessage("3. Image");
        terminalMediaVar.displayMessage("4. Book");
        int mediaTypeChoice;//storing users choice

        do {
            mediaTypeChoice = terminalMediaVar.getInt("Elige el tipo: 1-4");
            if (mediaTypeChoice > 4 || mediaTypeChoice < 1 ){
                terminalMediaVar.displayMessage("Invalid option. Please choose a number between 1 and 4.");
            }
        } while (mediaTypeChoice > 4 || mediaTypeChoice < 1 );

        if(mediaTypeChoice == 1){
            terminalMediaVar.getString("Enter Video Name: ");
        }

        return mediaTypeChoice;

    }

}
