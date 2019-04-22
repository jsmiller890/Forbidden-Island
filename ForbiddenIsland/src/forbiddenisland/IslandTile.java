package forbiddenisland;

/**
 *
 * @author Jeremy Miller
 */

public class IslandTile {
    String tileFullName;
    String firstWord = "           ";
    String secondWord = "           ";
    String thirdWord = "           ";
    String floodStatus = "           ";
    String player1 = "  ";
    String player2 = "  ";
    String player3 = "  ";
    String player4 = "  ";
    String tileRow;
    
    int tileColumn;
    
    String line_1 = "|-------------|";
    String line_2 = String.format("| %s |", floodStatus);
    String line_3 = "|             |";
    String line_4 = "|             |";
    String line_5 = "|             |";
    String line_6 = String.format("| %1$s %2$s %3$s %4$s |", player1, player2, player3, player4);
    String line_7 = "|             |";
    String line_8 = "|-------------|";
    
    public void IslandTile(String tileFullName, String firstWord, String secondWord, String thirdWord,
                            String floodStatus, String player1, String player2, String player3, String player4,
                            String tileRow, int tileColumn, String line_1, String line_2, String line_3,
                            String line_4, String line_5, String line_6, String line_7, String line_8) {
        
        this.tileFullName = tileFullName;
        this.firstWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
        this.floodStatus = floodStatus;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.tileRow = tileRow;
        this.tileColumn = tileColumn;
        this.line_1 = line_1;
        this.line_2 = line_2;
        this.line_3 = line_3;
        this.line_4 = line_4;
        this.line_5 = line_5;
        this.line_6 = line_6;
        this.line_7 = line_7;
        this.line_8 = line_8;
        
        SplitTileName(tileFullName);
    }
    
    public String CheckName(String wordToCheck){
        for(int i = wordToCheck.length(); i < 11; i++)
        {
            wordToCheck = wordToCheck + " ";
        }
        return wordToCheck;
    }
    
    private void SplitTileName(String tileName){
        tileFullName = tileName;
        if(tileName.contains("of the")){
            secondWord = "of the     ";
            String[] tileSplitName = tileName.split(" ");
            firstWord = CheckName(tileSplitName[0]);
            thirdWord = CheckName(tileSplitName[3]);
        }
        else{
            String[] tileSplitName = tileName.split(" ");
            for(int i = 0; i < tileSplitName.length; i++)
            {
                switch(i){
                    case 0:
                        firstWord = CheckName(tileSplitName[i]);
                        break;
                    case 1:
                        secondWord = CheckName(tileSplitName[i]);
                        break;
                    case 2:
                        thirdWord = CheckName(tileSplitName[i]);
                        break;
                }
            }
        }
    }
    
    public void SetFlooded(){
        if(floodStatus.equals("           ")){
            floodStatus = "//FLOODED//";
        }
        else if (floodStatus.equals("//FLOODED//")){
            floodStatus = "**REMOVED**";
        }
    }
    
    public void SetShored(){
        floodStatus = "           ";
    }
    
    public void SetTileRow(String row){
        tileRow = row;
    }
    
    public void SetTileColumn(int column){
        tileColumn = column;
    }
    
    public void SetTile(){
        line_1 = "|-------------|";
        line_2 = String.format("| %s |", floodStatus);
        line_3 = String.format("| %s |", firstWord);
        line_4 = String.format("| %s |", secondWord);
        line_5 = String.format("| %s |", thirdWord);
        line_6 = String.format("| %1$s %2$s %3$s %4$s |", player1, player2, player3, player4);
        line_7 = String.format("|       %1$d%2$d        |", tileRow, tileColumn);
        line_8 = "|-------------|";
    }
    
    public void SetPlayerLocations(Player[] player){
        if(player[0].row == tileRow && player[0].column == tileColumn){
            player1 = "P1";
        }
        else{
            player1 = "  ";
        }
        if(player[1].row == tileRow && player[1].column == tileColumn){
            player2 = "P1";
        }
        else {
            player2 = "  ";
        }
        if(player[2].row == tileRow && player[2].column == tileColumn){
            player3 = "P1";
        }
        else {
            player3 = "  ";
        }
        if(player[3].row == tileRow && player[3].column == tileColumn){
            player4 = "P1";
        }
        else {
            player4 = "  ";
        }
    }
}
