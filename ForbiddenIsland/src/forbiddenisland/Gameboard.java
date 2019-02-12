package forbiddenisland;
import java.util.Scanner;
import java.util.Arrays;

/**
 *  FIXME: Shows all tiles surrounding player for shore up. Only show tiles that are under water.
 *       : Add a show map function on option menu.
 *       : Figure out why it says character is trapped while not really trapped.
 * @author Jeremy Miller
 */
public class Gameboard {
    
    //Loading the other classes
    
    IslandTile[] IslandTile = new IslandTile[24];
    FloodCardDeck FloodDeck = new FloodCardDeck();
    Player[] Player;
    TreasureCardDeck TreasureCards = new TreasureCardDeck();
    
    //Declarations of variables
    
    int[] waterLevel = {2, 2, 3, 3, 3, 4, 4, 5, 5, 6};
    int currentWaterLevel = 0;
    int cardNumber = 0;
    int[] outofBounds = {2, 3, 4, 5, 11, 12, 15, 16, 20, 21, 26, 30, 37, 40, 
                         47, 50, 51, 56, 57, 61, 62, 65, 66, 67, 72, 73, 74, 75};
    int[] possibleTiles = {13, 14, 22, 23, 24, 25, 31, 32, 33, 34, 35, 36,
                           41, 42, 43, 44, 45, 46, 52, 53, 54, 55, 63, 64};
    int playerCount = 0;
    String[] treasureLeft = {"Earth Stone", "Fire Crystal", "Water Chalice", "Wind Statue"};
    boolean endGame = false;
    String[] adventurers = {"Navigator", "Diver", "Messenger", "Engineer", "Pilot", "Explorer"};
    Player[] movablePlayers;
    int navMoveChoice = 0;
    int shoredChoice = 0;
    Player messenger;
    int cardTradeChoice = 0;
    
    Scanner scnr = new Scanner(System.in);
    
    
    public void Gameboard(IslandTile[] IslandTile, FloodCardDeck FloodDeck,
                          int cardNumber, Player[] Player, TreasureCardDeck TreasureCards,
                          int[] outofBounds, int[] possibleTiles, int playerCount,
                          int[] waterLevel, int currentWaterLevel, String[] adventurers,
                          boolean endGame, String[] treasureLeft)
    {
        this.IslandTile = IslandTile;
        this.FloodDeck = FloodDeck;
        this.cardNumber = cardNumber;
        this.Player = Player;
        this.TreasureCards = TreasureCards;
        this.outofBounds = outofBounds;
        this.possibleTiles = possibleTiles;
        this.playerCount = playerCount;
        this.waterLevel = waterLevel;
        this.currentWaterLevel = currentWaterLevel;
        this.adventurers = adventurers;
        this.treasureLeft = treasureLeft;
        this.endGame = endGame;
    }
    
    //Creates the tiles to be used for the gameboard
    
    public void CreateTiles()
    {
        for(int i = 0; i < IslandTile.length; i++)
        {
            IslandTile[i] = new IslandTile();
            IslandTile[i].tilePane = i;
        }
        FloodDeck.Shuffle();
        FloodDeck.Shuffle();
        FloodDeck.Shuffle();
        for(int i = 0; i < IslandTile.length; i++)
        {
            System.out.print(IslandTile[i].firstWord);
            IslandTile[i].SplitName(FloodDeck.GetFloodCard(cardNumber));
            AssignTileLocation(i);
            cardNumber++;
        }
    }
    
    //Gives each tile a number based on row and column on the gameboard
    
    public void AssignTileLocation(int tileNumber)
    {
        IslandTile[tileNumber].tileLocation = possibleTiles[tileNumber];
    }
    
    //Sets tile state from shored to flooded or flooded to removed
    
    public void SetFlooded(int tileNumber)
    {
        switch(IslandTile[tileNumber].floodStatus)
        {
            case "           ":
                IslandTile[tileNumber].SetFloodStatus("//Flooded//");
                break;
            case "//Flooded//":
                IslandTile[tileNumber].SetFloodStatus("**Removed**");
                break;
        }
    }
    
    //Sets the tile to shored
    
    public void SetShored(int tileNumber)
    {
        for(int a = 0; a < IslandTile.length; a++)
        {
            if(IslandTile[a].tileLocation == tileNumber)
            {
                tileNumber = IslandTile[a].tilePane;
            }
        }    
        switch(IslandTile[tileNumber].floodStatus)
        {
            case "//Flooded//":
                IslandTile[tileNumber].floodStatus = "           ";
                break;
        }
    }
    
    //Checks which tile card has been drawn then calls setflooded method
    
    public void FloodTile()
    {
        for (int i = 0; i < IslandTile.length; i++)
        {
            if (IslandTile[i].tileName.equals(FloodDeck.drawnCard))
            {
                SetFlooded(IslandTile[i].tilePane);
            }
        }
    }
    
    //Gameboard that serves a map for the players
    
    public void PrintGameboard()
    {
        System.out.print  ("                            |-------------|-------------|\n"
                         + "                            | "+IslandTile[0].floodStatus+" | "+IslandTile[1].floodStatus+" |\n"
                         + "                            | "+IslandTile[0].firstWord+" | "+IslandTile[1].firstWord+" |\n"
                         + "                            | "+IslandTile[0].secondWord+" | "+IslandTile[1].secondWord+" |\n"
                         + "                            | "+IslandTile[0].thirdWord+" | "+IslandTile[1].thirdWord+" |\n"
                         + "                            | "+IslandTile[0].player1+" "+IslandTile[0].player2+" "+IslandTile[0].player3+" "+IslandTile[0].player4+" | "+IslandTile[1].player1+" "+IslandTile[1].player2+" "+IslandTile[1].player3+" "+IslandTile[1].player4+" |\n"
                         + "                            |       13    |      14     |\n"
                         + "              |-------------|-------------|-------------|-------------|\n"
                         + "              | "+IslandTile[2].floodStatus+" | "+IslandTile[3].floodStatus+" | "+IslandTile[4].floodStatus+" | "+IslandTile[5].floodStatus+" |\n"
                         + "              | "+IslandTile[2].firstWord+" | "+IslandTile[3].firstWord+" | "+IslandTile[4].firstWord+" | "+IslandTile[5].firstWord+" |\n"
                         + "              | "+IslandTile[2].secondWord+" | "+IslandTile[3].secondWord+" | "+IslandTile[4].secondWord+" | "+IslandTile[5].secondWord+" |\n"
                         + "              | "+IslandTile[2].thirdWord+" | "+IslandTile[3].thirdWord+" | "+IslandTile[4].thirdWord+" | "+IslandTile[5].thirdWord+" |\n"
                         + "              | "+IslandTile[2].player1+" "+IslandTile[2].player2+" "+IslandTile[2].player3+" "+IslandTile[2].player4+" | "+IslandTile[3].player1+" "+IslandTile[3].player2+" "+IslandTile[3].player3+" "+IslandTile[3].player4+" | "+IslandTile[4].player1+" "+IslandTile[4].player2+" "+IslandTile[4].player3+" "+IslandTile[4].player4+" | "+IslandTile[5].player1+" "+IslandTile[5].player2+" "+IslandTile[5].player3+" "+IslandTile[5].player4+" |\n"
                         + "              |      22     |      23     |      24     |      25     |\n"
                         + "|-------------|-------------|-------------|-------------|-------------|-------------|\n"
                         + "| "+IslandTile[6].floodStatus+" | "+IslandTile[7].floodStatus+" | "+IslandTile[8].floodStatus+" | "+IslandTile[9].floodStatus+" | "+IslandTile[10].floodStatus+" | "+IslandTile[11].floodStatus+" |\n"
                         + "| "+IslandTile[6].firstWord+" | "+IslandTile[7].firstWord+" | "+IslandTile[8].firstWord+" | "+IslandTile[9].firstWord+" | "+IslandTile[10].firstWord+" | "+IslandTile[11].firstWord+" |\n"
                         + "| "+IslandTile[6].secondWord+" | "+IslandTile[7].secondWord+" | "+IslandTile[8].secondWord+" | "+IslandTile[9].secondWord+" | "+IslandTile[10].secondWord+" | "+IslandTile[11].secondWord+" |\n"
                         + "| "+IslandTile[6].thirdWord+" | "+IslandTile[7].thirdWord+" | "+IslandTile[8].thirdWord+" | "+IslandTile[9].thirdWord+" | "+IslandTile[10].thirdWord+" | "+IslandTile[11].thirdWord+" |\n"
                         + "| "+IslandTile[6].player1+" "+IslandTile[6].player2+" "+IslandTile[6].player3+" "+IslandTile[6].player4+" | "+IslandTile[7].player1+" "+IslandTile[7].player2+" "+IslandTile[7].player3+" "+IslandTile[7].player4+" | "+IslandTile[8].player1+" "+IslandTile[8].player2+" "+IslandTile[8].player3+" "+IslandTile[8].player4+" | "+IslandTile[9].player1+" "+IslandTile[9].player2+" "+IslandTile[9].player3+" "+IslandTile[9].player4+" | "+IslandTile[10].player1+" "+IslandTile[10].player2+" "+IslandTile[10].player3+" "+IslandTile[10].player4+" | "+IslandTile[11].player1+" "+IslandTile[11].player2+" "+IslandTile[11].player3+" "+IslandTile[11].player4+" |\n"
                         + "|      31     |     32      |      33     |      34     |      35     |      36     |\n"
                         + "|-------------|-------------|-------------|-------------|-------------|-------------|\n"
                         + "| "+IslandTile[12].floodStatus+" | "+IslandTile[13].floodStatus+" | "+IslandTile[14].floodStatus+" | "+IslandTile[15].floodStatus+" | "+IslandTile[16].floodStatus+" | "+IslandTile[17].floodStatus+" |\n"
                         + "| "+IslandTile[12].firstWord+" | "+IslandTile[13].firstWord+" | "+IslandTile[14].firstWord+" | "+IslandTile[15].firstWord+" | "+IslandTile[16].firstWord+" | "+IslandTile[17].firstWord+" |\n"
                         + "| "+IslandTile[12].secondWord+" | "+IslandTile[13].secondWord+" | "+IslandTile[14].secondWord+" | "+IslandTile[15].secondWord+" | "+IslandTile[16].secondWord+" | "+IslandTile[17].secondWord+" |\n"
                         + "| "+IslandTile[12].thirdWord+" | "+IslandTile[13].thirdWord+" | "+IslandTile[14].thirdWord+" | "+IslandTile[15].thirdWord+" | "+IslandTile[16].thirdWord+" | "+IslandTile[17].thirdWord+" |\n"
                         + "| "+IslandTile[12].player1+" "+IslandTile[12].player2+" "+IslandTile[12].player3+" "+IslandTile[12].player4+" | "+IslandTile[13].player1+" "+IslandTile[13].player2+" "+IslandTile[13].player3+" "+IslandTile[13].player4+" | "+IslandTile[14].player1+" "+IslandTile[14].player2+" "+IslandTile[14].player3+" "+IslandTile[14].player4+" | "+IslandTile[15].player1+" "+IslandTile[15].player2+" "+IslandTile[15].player3+" "+IslandTile[15].player4+" | "+IslandTile[16].player1+" "+IslandTile[16].player2+" "+IslandTile[16].player3+" "+IslandTile[16].player4+" | "+IslandTile[17].player1+" "+IslandTile[17].player2+" "+IslandTile[17].player3+" "+IslandTile[17].player4+" |\n"
                         + "|     41      |      42     |     43      |      44     |      45     |      46     |\n"
                         + "|-------------|-------------|-------------|-------------|-------------|-------------|\n"
                         + "              | "+IslandTile[18].floodStatus+" | "+IslandTile[19].floodStatus+" | "+IslandTile[20].floodStatus+" | "+IslandTile[21].floodStatus+" |\n"
                         + "              | "+IslandTile[18].firstWord+" | "+IslandTile[19].firstWord+" | "+IslandTile[20].firstWord+" | "+IslandTile[21].firstWord+" |\n"
                         + "              | "+IslandTile[18].secondWord+" | "+IslandTile[19].secondWord+" | "+IslandTile[20].secondWord+" | "+IslandTile[21].secondWord+" |\n"
                         + "              | "+IslandTile[18].thirdWord+" | "+IslandTile[19].thirdWord+" | "+IslandTile[20].thirdWord+" | "+IslandTile[21].thirdWord+" |\n"
                         + "              | "+IslandTile[18].player1+" "+IslandTile[18].player2+" "+IslandTile[18].player3+" "+IslandTile[18].player4+" | "+IslandTile[19].player1+" "+IslandTile[19].player2+" "+IslandTile[19].player3+" "+IslandTile[19].player4+" | "+IslandTile[20].player1+" "+IslandTile[20].player2+" "+IslandTile[20].player3+" "+IslandTile[20].player4+" | "+IslandTile[21].player1+" "+IslandTile[21].player2+" "+IslandTile[21].player3+" "+IslandTile[21].player4+" |\n"
                         + "              |     52      |      53     |      54     |      55     |\n"
                         + "              |-------------|-------------|-------------|-------------|\n"
                         + "                            | "+IslandTile[22].floodStatus+" | "+IslandTile[23].floodStatus+" |\n"
                         + "   \"8\": Treasure List       | "+IslandTile[22].firstWord+" | "+IslandTile[23].firstWord+" |\n"
                         + "   \"9\": Hand                | "+IslandTile[22].secondWord+" | "+IslandTile[23].secondWord+" |\n"
                         + "   \"0\": No action           | "+IslandTile[22].thirdWord+" | "+IslandTile[23].thirdWord+" |\n"
                         + "                            | "+IslandTile[22].player1+" "+IslandTile[22].player2+" "+IslandTile[22].player3+" "+IslandTile[22].player4+" | "+IslandTile[23].player1+" "+IslandTile[23].player2+" "+IslandTile[23].player3+" "+IslandTile[23].player4+" |\n"
                         + "                            |     63      |     64      |\n"
                         + "                            |-------------|-------------|\n");
    }
    
    //The first thing the player sees
    
    public void WelcomeScreen()
    {
        System.out.println("Welcome to Forbidden Island!");
        System.out.println("Adventure... If you dare...\n");
        System.out.println("Enter a command:");
        System.out.println("    R - Rules");
        System.out.println("    A - About");
        System.out.println("    S - Start Game");
        System.out.println("    E - Exit");
        String welcomeInput = "";
        while(!welcomeInput.equalsIgnoreCase("E"))
        {
            welcomeInput = scnr.next();
            switch(welcomeInput)
            {
                case "R":
                    System.out.println("Order of play:\n"
                            + "\tTake up to 3 actions.\n"
                            + "\tDraw 2 treasure cards.\n"
                            + "\tDraw flood cards equal to water level.\n");
                    System.out.println("Movement - Players can only move in the 4 cardinal directions.\n"
                            + "\tUnless player is the explorer, who can move diagonally.\n"
                            + "\tPilot can move to any tile once a turn for 1 action.\n"
                            + "\tNavigator can move other players up to 2 adjacent tiles per action.\n"
                            + "\tDiver may move through //Flooded// or **Removed** tiles\n");
                    System.out.println("Shore up - Players are able to use 1 action to shore up"
                            + " a tile that has been flooded.\n"
                            + "\tEngineer may shore up 2 tiles with 1 action.\n"
                            + "\tExplorer may shore up tiles diagonally.\n");
                    System.out.println("Treasures - To claim a treasure a player must discard 4 "
                            + "matching treasure cards while it is on a corresponding tile.\n"
                            + "\tIf 2 players are on the same tile they may trade treasure cards"
                            + " for 1 action per card.\n"
                            + "\tTreasures may be captured on flooded tiles.\n"
                            + "\tMessenger can give treasure cards without being on the same tile.\n");
                    break;
                case "A":
                    System.out.println("Your team of adventurers must work together to keep forbidden"
                            + " island from sinking,\nin order to buy enough time to capture its four"
                            + " treasures. Once you've captured them, you must make it\nto Fool's Landing"
                            + " and escape by helicopter to win. If however, the island sinks before "
                            + "you can complete your tasks, the mission ends in defeat.");
                    break;
                case "S":
                    System.out.println("How many players (2-4):");
                    playerCount = scnr.nextInt();
                    while(playerCount < 2 || playerCount > 4)
                    {
                        System.out.println("Please enter a valid number (2-4):");
                        playerCount = scnr.nextInt();
                    }
                    Player = new Player[playerCount];
                    for(int i = 0; i < playerCount; i++)
                    {
                        Player[i] = new Player();
                    }
                   return;
                case "E":
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid option");
                    break;        
            }
        }
    }
    
    //The setup process for the game (eg. shuffle decks of cards, assign players adventurer titles
    
    public void Setup()
    {
        TreasureCards.ShuffleDeck();
        TreasureCards.ShuffleDeck();
        TreasureCards.ShuffleDeck();
        
        CreateTiles();
        
        FloodDeck.Shuffle();
        FloodDeck.Shuffle();
        FloodDeck.Shuffle();

        for(int i = 0; i < 5; i++)
        {
            FloodDeck.DrawCard();
            FloodTile();
        }
        
        for(int i = 0; i < 2; i++)
        {
            for (int j = 0; j < Player.length; j++)
            {
                Player[j].AddCardToHand(TreasureCards.DrawCard());
                while (TreasureCards.drawnCard.equals("Waters Rise"))
                {
                    TreasureCards.treasureCards[TreasureCards.cardsLeftInDeck] = "Waters Rise";
                    Player[j].hand[i] = TreasureCards.DrawCard();
                }
            }
        }
        SetAdventurer();
        SetPlayerStartingLocation();
        for(IslandTile t : IslandTile)
        {
            t.SetPlayerLocation(PlayerTileCheck());
        }
        PrintGameboard();
    }
    public void WatersRise()
    {
        currentWaterLevel++;
        if(currentWaterLevel == 9)
        {
            endGame = true;
            return;
        }
        FloodDeck.Refresh();
    }
    
    //The turn each player takes until the game is won or lost
    
    public void PlayerTurn()
    {
        while(endGame != true)
        {
            for (int i = 0; i < playerCount; i++)
            {
                System.out.println("Player " + (i + 1));
                System.out.println(Player[i].adventurer);
                endGame = Player[i].PlayerCurrentLocationCheck(this.IslandTile, this.endGame);
                for (int j = 0; j < 3; j++)
                {
                    System.out.println("Choose an action:");
                    System.out.println("1. Move\t\t\t\t 7. Treasure Card Discard\n2. Shore Up\t\t\t 8. Treasures Left"
                            + "\n3. Give a Treasure Card\t\t 9. Hand\n4. Capture a Treasure\t\t 0. No action");
                    int actionChoice = scnr.nextInt();
                    while(actionChoice > 9 && actionChoice < 0)
                    {
                        System.out.println("Please enter a valid choice: ");
                        actionChoice = scnr.nextInt();
                    }                    
                    switch(actionChoice)
                        {
                            case 1:
                                //move
                                if(Player[i].adventurer.equals("Pilot"))
                                {
                                    Player[i].possibleMoves = possibleTiles;
                                    System.out.println(Arrays.toString(Player[i].possibleMoves));
                                    Player[i].MovePlayer();
                                    break;
                                }
                                else if(Player[i].adventurer.equals("Diver"))
                                {
                                    Player[i].possibleMoves[0] = Player[i].position - 1;
                                    Player[i].possibleMoves[1] = Player[i].position + 1;
                                    Player[i].possibleMoves[2] = Player[i].position + 10;
                                    Player[i].possibleMoves[3] = Player[i].position - 10;
                                    for(int d = 0; d < Player[i].possibleMoves.length; d++)
                                    {
                                        for(int e = 0; e < IslandTile.length; e++)
                                        {
                                            if(IslandTile[e].tileLocation == Player[i].possibleMoves[d] 
                                            && (IslandTile[e].floodStatus.equals("Flooded")||IslandTile[e].floodStatus.equals("Removed")))
                                            {
                                                Player[i].possibleMoves[d] = Player[i].possibleMoves[d] - 1;
                                                Player[i].possibleMoves[d + 1] = Player[i].possibleMoves[d] + 1;
                                                Player[i].possibleMoves[d + 2] = Player[i].possibleMoves[d] + 10;
                                                Player[i].possibleMoves[d + 3] = Player[i].possibleMoves[d] - 10;
                                            }
                                        }
                                    }
                                    CheckValidTile(i);
                                    System.out.println(Arrays.toString(Player[i].possibleMoves));
                                    Player[i].MovePlayer();
                                    break;
                                }
                                else if(Player[i].adventurer.equals("Navigator"))
                                {
                                    System.out.println("Move another character? (y/n)");
                                    String moveCharacter = scnr.next();
                                    while(!moveCharacter.toLowerCase().equals("n") && !moveCharacter.toLowerCase().equals("y"))
                                    {
                                        System.out.println("Please enter a valid choice (y/n");
                                        moveCharacter = scnr.next();
                                    }
                                    if(moveCharacter.toLowerCase().equals("y"))
                                    {
                                        movablePlayers = new Player[playerCount - 1];
                                        int movables = 0;
                                        for(Player a : Player)
                                        {
                                            if(!a.adventurer.equals("Navigator"))
                                            {
                                                movablePlayers[movables] = a;
                                                movables++;
                                            }
                                        }
                                        System.out.println("Which character would you like to move?");
                                        for(int h = 0; h < movablePlayers.length; h++)
                                        {
                                            System.out.println((h+1) + " : " + movablePlayers[h].adventurer);
                                        }
                                        while(navMoveChoice > 0 && navMoveChoice < playerCount)
                                        {
                                            navMoveChoice = scnr.nextInt();
                                        }
                                        movablePlayers[navMoveChoice - 1].SetPossibleMoves();
                                    }
                                    else
                                    {
                                        Player[i].possibleMoves[0] = Player[i].position - 1;
                                        Player[i].possibleMoves[1] = Player[i].position + 1;
                                        Player[i].possibleMoves[2] = Player[i].position + 10;
                                        Player[i].possibleMoves[3] = Player[i].position - 10;
                                        CheckValidTile(i);
                                        System.out.println(Arrays.toString(Player[i].possibleMoves));
                                        Player[i].MovePlayer();
                                    }
                                    break;
                                }
                                else if (Player[i].adventurer.equals("Explorer"))
                                {
                                    Player[i].possibleMoves[0] = Player[i].position - 1;
                                    Player[i].possibleMoves[1] = Player[i].position + 9;
                                    Player[i].possibleMoves[2] = Player[i].position + 10;
                                    Player[i].possibleMoves[3] = Player[i].position + 11;
                                    Player[i].possibleMoves[4] = Player[i].position + 1;
                                    Player[i].possibleMoves[5] = Player[i].position - 11;
                                    Player[i].possibleMoves[6] = Player[i].position - 10;
                                    Player[i].possibleMoves[7] = Player[i].position - 9;
                                    System.out.println(Arrays.toString(Player[i].possibleMoves));
                                    Player[i].MovePlayer();
                                    break;
                                }
                                else
                                {
                                Player[i].possibleMoves[0] = Player[i].position - 1;
                                Player[i].possibleMoves[1] = Player[i].position + 1;
                                Player[i].possibleMoves[2] = Player[i].position + 10;
                                Player[i].possibleMoves[3] = Player[i].position - 10;
                                System.out.println(Arrays.toString(Player[i].possibleMoves));
                                Player[i].MovePlayer();
                                break;
                                }
                                
                                //FIXME: Navigator currently only moves others 1 space
                            case 2:
                                System.out.println("Which island do you want to shore up?");
                                if(Player[i].adventurer.equals("Engineer"))
                                {
                                    for(int k = 0; k < 2; k++)
                                    {
                                        Player[i].possibleMoves[0] = Player[i].position - 1;
                                        Player[i].possibleMoves[1] = Player[i].position + 1;
                                        Player[i].possibleMoves[2] = Player[i].position + 10;
                                        Player[i].possibleMoves[3] = Player[i].position - 10;
                                        for(int a = 0; a < Player[i].possibleMoves.length; a++)
                                        {
                                            if(Player[i].possibleMoves[a] != 0)
                                            {
                                                System.out.println(a + ": " + Player[i].possibleMoves[a]);
                                            }
                                        }
                                        shoredChoice = scnr.nextInt();
                                        SetShored(Player[i].possibleMoves[shoredChoice]);
                                    }
                                    break;
                                }
                                else if (Player[i].adventurer.equals("Explorer"))
                                {
                                    Player[i].possibleMoves[0] = Player[i].position - 1;
                                    Player[i].possibleMoves[1] = Player[i].position - 11;
                                    Player[i].possibleMoves[2] = Player[i].position - 10;
                                    Player[i].possibleMoves[3] = Player[i].position - 9;
                                    Player[i].possibleMoves[4] = Player[i].position + 1;
                                    Player[i].possibleMoves[5] = Player[i].position + 9;
                                    Player[i].possibleMoves[6] = Player[i].position + 10;
                                    Player[i].possibleMoves[7] = Player[i].position + 11;
                                    for(int a = 0; a < Player[i].possibleMoves.length; a++)
                                    {
                                        if(Player[i].possibleMoves[a] != 0)
                                        {
                                            System.out.println(a + ": " + Player[i].possibleMoves[a]);
                                        }
                                    }
                                    shoredChoice = scnr.nextInt();
                                    SetShored(Player[i].possibleMoves[shoredChoice]);
                                    break;
                                }
                                else
                                {
                                    for(int n = 0; n < 4; n++)
                                    {
                                        Player[i].possibleMoves[n] = Player[i].position - 1;
                                        Player[i].possibleMoves[n] = Player[i].position + 1;
                                        Player[i].possibleMoves[n] = Player[i].position + 10;
                                        Player[i].possibleMoves[n] = Player[i].position - 10;
                                    }
                                    for(int a = 0; a < Player[i].possibleMoves.length; a++)
                                    {
                                        if(Player[i].possibleMoves[a] != 0)
                                        {
                                            System.out.println(a + ": " + Player[i].possibleMoves[a]);
                                        }
                                    }
                                    shoredChoice = scnr.nextInt();
                                    SetShored(Player[i].possibleMoves[shoredChoice]);
                                    break;
                                }
                            case 3:
                                if(Player[i].adventurer.equals("Messenger"))
                                {
                                    int movables = 0;
                                    for(Player b : Player)
                                    {
                                        if(!b.adventurer.equals("Messenger"))
                                        {
                                            movablePlayers[movables] = b;
                                            movables++;
                                        }
                                        else
                                        {
                                            messenger = b;
                                        }
                                    }
                                    System.out.println("Which player would you like to give a treasure card to?");
                                    for(int o = 0; o < movablePlayers.length; o++)
                                    {
                                        System.out.println(o + ": " + movablePlayers[o].adventurer);
                                    }
                                    navMoveChoice = scnr.nextInt();
                                    if(navMoveChoice > -1 && navMoveChoice < playerCount)
                                    {
                                        for(int p = 0; p < messenger.hand.length; p++)
                                        {
                                            System.out.println(p + ": " + messenger.hand[p]);
                                        }
                                        System.out.println("Which card would you like to give?");
                                        cardTradeChoice = scnr.nextInt();
                                        if(cardTradeChoice > -1 && cardTradeChoice < messenger.hand.length)
                                        {
                                            Player[navMoveChoice].hand[Player[navMoveChoice].cardsInHand + 1] = messenger.hand[cardTradeChoice];
                                            Player[navMoveChoice].cardsInHand++;
                                            if(Player[navMoveChoice].cardsInHand > 5)
                                            {
                                                System.out.println(Player[i].adventurer + " select a card to discard");
                                                Player[navMoveChoice].PlayerHandCheck(this.TreasureCards);
                                            }
                                        }
                                    }
                                }
                                else
                                {
                                    int movables = 0;
                                    for(Player b : Player)
                                    {
                                        if(!b.adventurer.equals(Player[i].adventurer) && b.position == Player[i].position)
                                        {
                                            movablePlayers[movables] = b;
                                            movables++;
                                        }
                                        else
                                        {
                                            messenger = b;
                                        }
                                    }
                                    System.out.println("Which player would you like to give a treasure card to?");
                                    for(int o = 0; o < movablePlayers.length; o++)
                                    {
                                        System.out.println(o + ": " + movablePlayers[o].adventurer);
                                    }
                                    navMoveChoice = scnr.nextInt();
                                    if(navMoveChoice > -1 && navMoveChoice < playerCount)
                                    {
                                        for(int p = 0; p < messenger.hand.length; p++)
                                        {
                                            System.out.println(p + ": " + messenger.hand[p]);
                                        }
                                        System.out.println("Which card would you like to give?");
                                        cardTradeChoice = scnr.nextInt();
                                        if(cardTradeChoice > -1 && cardTradeChoice < messenger.hand.length)
                                        {
                                            Player[navMoveChoice].hand[Player[navMoveChoice].cardsInHand + 1] = messenger.hand[cardTradeChoice];
                                            Player[navMoveChoice].cardsInHand++;
                                            if(Player[navMoveChoice].cardsInHand > 5)
                                            {
                                                System.out.println(Player[i].adventurer + " select a card to discard");
                                                Player[navMoveChoice].PlayerHandCheck(this.TreasureCards);
                                            }
                                        }
                                    }
                                }
                                //give treasure card
                                //messenger can give cards without being on same tile
                                
                            case 4:
                                //capture treasure
                                for(int a = 0; a < IslandTile.length; a++)
                                {
                                    if(IslandTile[a].tileLocation == Player[i].position)
                                    {
                                        j = j + Player[i].CaptureTreasure(IslandTile[a].tileName);
                                    }
                                }
                                break;
                                
                            case 7:
                                System.out.println(Arrays.toString(TreasureCards.treasureDiscard));
                                j--;
                                break;
                                
                            case 8:
                                System.out.println(Arrays.toString(treasureLeft));
                                j--;
                                break;
                                
                            case 9:
                                Player[i].GetPlayerHand();
                                j--;
                                break;
                                
                            case 0:
                                break;
                                
                            default:
                                System.out.println("Please enter a valid choice as integer");
                                actionChoice = scnr.nextInt();
                        }
                }
                if(TreasureCards.treasureCards[0].equals("Waters Rise"))
                {
                    WatersRise();
                    TreasureCards.Discard(this.TreasureCards.treasureCards[0]);
                }
                else if(TreasureCards.treasureCards[0].equals("no card"))
                {
                    TreasureCards.Refresh();
                }
                Player[i].AddCardToHand(TreasureCards.DrawCard());
                if(TreasureCards.treasureCards[0].equals("Waters Rise"))
                {
                    WatersRise();
                    TreasureCards.Discard(this.TreasureCards.treasureCards[0]);
                }
                else if(TreasureCards.treasureCards[0].equals("no card"))
                {
                    TreasureCards.Refresh();
                }
                Player[i].AddCardToHand(TreasureCards.DrawCard());
                for(int a = 0; a < waterLevel[currentWaterLevel]; a++)
                {
                    FloodDeck.DrawCard();
                    FloodTile();
                }
                Player[i].PlayerHandCheck(this.TreasureCards);
                for(IslandTile t : IslandTile)
                {
                    t.SetPlayerLocation(PlayerTileCheck());
                }
                PrintGameboard();
                TreasureLostCheck();
            }
        }
    }
    
    //Checks if the player entered number matches a possible move
    
    public void CheckValidTile(int player)
    {
        for(int f = 0; f < Player[player].possibleMoves.length; f++)
        {
            for(int g = 0; g < outofBounds.length; g++)
            {
                if(Player[player].possibleMoves[f] == outofBounds[g] || Player[player].possibleMoves[f] < 0)
                {
                    Player[player].possibleMoves[f] = 0;
                }
            }
        }
    }
    
    //Sets player to an adventurer
    
    public void SetAdventurer()
    {
        for (int i = 0; i < adventurers.length; i++) {
            int index = (int) (Math.random() * adventurers.length);
            String temp = adventurers[index];
            adventurers[index] = adventurers[i];
            adventurers[i] = temp;
        }
        for(int i = 0; i < playerCount; i++)
        {
            Player[i].adventurer = adventurers[i];
        }
    }
    
    //Sets player starting tile (this is always the same)
    
    public void SetPlayerStartingLocation()
    {
        for(Player c : Player)
        {
            for(IslandTile a : IslandTile)
            {
                if(c.adventurer.equals("Pilot") && a.tileName.equals("Fool\'s Landing"))
                {
                    c.position = a.tileLocation;
                }
                else if (c.adventurer.equals("Engineer") && a.tileName.equals("Bronze Gate"))
                {
                    c.position = a.tileLocation;
                }
                else if (c.adventurer.equals("Navigator") && a.tileName.equals("Gold Gate"))
                {
                    c.position = a.tileLocation;
                }
                else if (c.adventurer.equals("Explorer") && a.tileName.equals("Copper Gate"))
                {
                    c.position = a.tileLocation;
                }
                else if (c.adventurer.equals("Messenger") && a.tileName.equals("Silver Gate"))
                {
                    c.position = a.tileLocation;
                }
                else if (c.adventurer.equals("Diver") && a.tileName.equals("Iron Gate"))
                {
                    c.position = a.tileLocation;
                }
            }
        }
    }
    
    //Checks if the tiles containing treasures has been lost to the depths and the game is lost
    
    public void TreasureLostCheck()
    {
        for(IslandTile t : IslandTile)
        {
            if(((t.tileName.equals("Tidal Palace") && t.floodStatus.equals("**Removed**")) && (t.tileName.equals("Coral Palace") && t.floodStatus.equals("*Removed**")))
            || ((t.tileName.equals("Temple of the Sun") && t.floodStatus.equals("**Removed**")) && (t.tileName.equals("Temple of the Moon") && t.floodStatus.equals("**Removed**")))
            || ((t.tileName.equals("Cave of Shadows") && t.floodStatus.equals("**Removed**")) && (t.tileName.equals("Cave of Embers") && t.floodStatus.equals("**Removed**")))
            || ((t.tileName.equals("Whispering Garden") && t.floodStatus.equals("**Removed**")) && (t.tileName.equals("Howling Garden") && t.floodStatus.equals("**Removed**"))))
            {
                endGame = true;
            }
        }
    }
    
    //Checks if the player is on a flooded or removed tile
    
    public int[] PlayerTileCheck()
    {
        int[] playerLocations = new int[4];
        int playerLocCount = 0;
        for(Player p : Player)
        {
            playerLocations[playerLocCount] = p.position;
            playerLocCount++;
        }
        return playerLocations;
    }
}
