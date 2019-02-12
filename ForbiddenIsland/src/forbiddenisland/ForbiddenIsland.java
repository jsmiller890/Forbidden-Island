package forbiddenisland;

import java.io.*;
import static java.lang.System.in;
import java.util.Arrays;

/**
 *
 * @author Jeremy Miller
 */
public class ForbiddenIsland {


    public static void main(String[] args) {
        Gameboard Gameboard = new Gameboard();
        
        Gameboard.WelcomeScreen();
        
        Gameboard.Setup();
        Gameboard.SetPlayerStartingLocation();
        
        Gameboard.PlayerTurn();
        
    }    
    
}
