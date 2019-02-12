package forbiddenisland;

/**
 *
 * @author Jeremy Miller
 */
public class IslandTile {
    String[] words;
    String firstWord = "";
    String secondWord = "";
    String thirdWord = "";
    String tileName = "";
    int tileLocation = 0;
    String floodStatus = "           "; //           , //Flooded//, **Removed**
    int tilePane = 0;
    String player1 = "  ";
    String player2 = "  ";
    String player3 = "  ";
    String player4 = "  ";
    
    
    public void IslandTile(String[] words, String firstWord, String secondWord, int tilePane,
                           String thirdWord, String floodStatus, int tileLocation, String tileName)
    {
        this.words = words;
        this.firstWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
        this.floodStatus = floodStatus;
        this.tileName = tileName;
        this.tileLocation = tileLocation;
        this.tilePane = tilePane;
    }
    
    
    public String CheckName(String wordToCheck)
    {
        for(int i = wordToCheck.length(); i < 11; i++)
        {
            wordToCheck = wordToCheck + " ";
        }
        return wordToCheck;
    }
    
    public String[] SplitName(String islandName)
    {
        tileName = islandName;
        if(islandName.contains("of the"))
        {
            secondWord = "of the     ";
            islandName = islandName.replace("of the", "the");
            words = islandName.split(" ");
            firstWord = words[0];
            words[1] = secondWord;
            thirdWord = words[2];
            firstWord = CheckName(firstWord);
            secondWord = CheckName(secondWord);
            thirdWord = CheckName(thirdWord);
            return words;
        }
        else
        {
            words = islandName.split(" ");
            for(int i = 0; i < words.length; i++)
            {
                switch(i){
                    case 0:
                        firstWord = words[i];
                        break;
                    case 1:
                        secondWord = words[i];
                        break;
                    case 2:
                        thirdWord = words[i];
                        break;
                }
            }
            firstWord = CheckName(firstWord);
            secondWord = CheckName(secondWord);
            thirdWord = CheckName(thirdWord);
            return words;
        }
        
    }
    public String GetFloodStatus()
    {
        return floodStatus;
    }
    public void SetFloodStatus(String changeStatus)
    {
        floodStatus = changeStatus;
    }
    public void SetPlayerLocation(int[] playerLocations)
    {
        if(playerLocations[0] == tileLocation)
        {
            player1 = "P1";
        }
        else if (playerLocations[0] != tileLocation)
        {
            player1 = "  ";
        }
        if (playerLocations[1] == tileLocation)
        {
            player2 = "P2";
        }
        else if (playerLocations[1] != tileLocation)
        {
            player2 = "  ";
        }
        if (playerLocations[2] == tileLocation)
        {
            player3 = "P3";
        }
        else if (playerLocations[2] != tileLocation)
        {
            player3 = "  ";
        }
        if (playerLocations[3] == tileLocation)
        {
            player4 = "P4";
        }
        else if (playerLocations[3] != tileLocation)
        {
            player4 = "  ";
        }
    }    
}
