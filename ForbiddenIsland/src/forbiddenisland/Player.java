package forbiddenisland;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Jeremy Miller
 */
public class Player {
    String[] hand = new String[9];
    int cardsInHand = 0;
    int currentCard = 0;
    int position = 0;
    int[] possibleMoves = new int[24];
    String color = "none";
    String startLocation = "none";
    String adventurer = "none";
    String currentTileName = "none";
    String[] piecesCollected = new String[4];
    int numOfEarthTreasure = 0;
    int numOfFireTreasure = 0;
    int numOfWaterTreasure = 0;
    int numOfWindTreasure = 0;
    Scanner scnr = new Scanner(System.in);
    int playerMoveChoice = 0;
    int currentTreasureCount = 0;
    
    
    
    
    
    public void Player(String[] hand, int cardsInHand, int position, int currentCard,
            String color, String startLocation, String[] piecesCollected, int numOfEarthTreasure,
            int numOfFireTreasure, int numOfWaterTreasure, int numOfWindTreasure,
            int[] possibleMoves, int playerMoveChoice, String currentTileName,
            int currentTreasureCount)
    {
        this.hand = hand;
        this.cardsInHand = cardsInHand;
        this.currentCard = currentCard;
        this.position = position;
        this.color = color;
        this.currentTileName = currentTileName;
        this.startLocation = startLocation;
        this.piecesCollected = piecesCollected;
        this.numOfEarthTreasure = numOfEarthTreasure;
        this.numOfFireTreasure = numOfFireTreasure;
        this.numOfWaterTreasure = numOfWaterTreasure;
        this.numOfWindTreasure = numOfWindTreasure;
        this.possibleMoves = possibleMoves;
        this.playerMoveChoice = playerMoveChoice;
        this.currentTreasureCount = currentTreasureCount;
    }
    
    public void GetPlayerHand()
    {
        System.out.println("Hand");
        System.out.println("----");
        for (int i = 0; i < hand.length; i++)
        {
            if(hand[i] != null && !hand[i].equals("no card"))
            {
                System.out.print(hand[i] + "\n");
            }
        }
    }
    
    public int GetPlayerLocation()
    {
        return position;
    }
    public void SetPlayerLocation(int newlocation)
    {
        position = newlocation;
    }
    
    public void AddCardToHand(String cardToAdd)
    {
        hand[cardsInHand] = cardToAdd;
        cardsInHand++;
    }
    
    public String GetStartLocation()
    {
        return startLocation;
    }
    
    public void GetPiecesCollected()
    {
        for(int i = 0; i < piecesCollected.length; i++)
        {
            if(piecesCollected[i] != null)
            {
                System.out.print(piecesCollected[i] + " ");
            }
        }
    }
    public void SetPiecesCollected(String pieceToAdd)
    {
        for(int i = 0; i < piecesCollected.length; i++)
        {
            if(piecesCollected[i] == null)
            {
                piecesCollected[i] = pieceToAdd;
            }
        }
    }
    public void CheckTreasureCards()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < hand.length; j++)
            {
                switch(hand[j])
                {
                    case "Earth Treasure":
                        numOfEarthTreasure++;
                        break;
                        
                    case "Fire Treasure":
                        numOfFireTreasure++;
                        break;
                        
                    case "Water Treasure":
                        numOfWaterTreasure++;
                        break;
                        
                    case "Wind Treasure":
                        numOfWindTreasure++;
                        break;
                }
            }
        }
    }
    
    public String GetAdventurer()
    {
        return adventurer;
    }
    public void SetAdventurer(String title)
    {
        adventurer = title;
    }
    
    public int[] GetPossibleMoves()
    {
        return possibleMoves;
    }
    public void SetPossibleMoves()
    {
        if (adventurer.equals("Explorer"))
        {
            possibleMoves[0] = position - 1;
            possibleMoves[1] = position + 9;
            possibleMoves[2] = position + 10;
            possibleMoves[3] = position + 11;
            possibleMoves[4] = position + 1;
            possibleMoves[5] = position - 11;
            possibleMoves[6] = position - 10;
            possibleMoves[7] = position - 9;
            System.out.println(Arrays.toString(possibleMoves));
            MovePlayer();
        }
        else
        {
            possibleMoves[0] = position - 1;
            possibleMoves[1] = position + 1;
            possibleMoves[2] = position + 10;
            possibleMoves[3] = position - 10;
            System.out.println(Arrays.toString(possibleMoves));
            MovePlayer();
        }
    }
    public boolean PlayerCurrentLocationCheck(IslandTile[] IslandTile, boolean endGame)
    {
        int validMoves = 0;
        int currentTile = 0;
        for(IslandTile tile : IslandTile)
        {
            if(tile.tileLocation == position)
            {
                currentTile = tile.tilePane;
            }
        }
        if(IslandTile[currentTile].floodStatus.equals("//Flooded//") || IslandTile[currentTile].floodStatus.equals("**Removed**"))
        {
            if (adventurer.equals("Explorer"))
            {
                possibleMoves[0] = position - 1;
                possibleMoves[1] = position + 9;
                possibleMoves[2] = position + 10;
                possibleMoves[3] = position + 11;
                possibleMoves[4] = position + 1;
                possibleMoves[5] = position - 11;
                possibleMoves[6] = position - 10;
                possibleMoves[7] = position - 9;
            }
            else
            {
                possibleMoves[0] = position - 1;
                possibleMoves[1] = position + 1;
                possibleMoves[2] = position + 10;
                possibleMoves[3] = position - 10;
            }
            for(int move : possibleMoves)
            {
                for(IslandTile tileCheck : IslandTile)
                {
                    if((move == tileCheck.tilePane) && (IslandTile[move].floodStatus.equals("//Flooded//") || IslandTile[move].floodStatus.equals("**Removed**")))
                    {
                        move = 0;
                    }
                }
            }
        }
        for(int move : possibleMoves)
        {
            if(move != 0)
            {
                validMoves++;
            }
        }
        if(validMoves > 0)
        {
            System.out.println(Arrays.toString(possibleMoves));
            MovePlayer();
        }
        else if(validMoves == 0)
        {
            endGame = true;
            System.out.println("The " + adventurer + " is trapped and can not escape.");
        }
        return endGame;
    }
    public void MovePlayer()
    {
        boolean playerMoveTest = false;
        while(!playerMoveTest)
        {
            System.out.println("Which tile would you like to move to?");
            playerMoveChoice = scnr.nextInt();
            for(int i = 0; i < possibleMoves.length; i++)
            {
                if(playerMoveChoice == possibleMoves[i])
                {
                    playerMoveTest = true;
                    position = playerMoveChoice;
                }
            }
        }
    }
    public void PlayerHandCheck(TreasureCardDeck treasureCards)
    {
        while (cardsInHand > 5)
        {
            Discard(treasureCards);
        }
    }
    
    public void Discard(TreasureCardDeck treasureCards)
    {
        System.out.println("You can only have 5 cards in your hand.");
        System.out.println("Which card would you like to discard?");
        
        for(int i = 0; i < cardsInHand; i++)
        {
            System.out.println(i + ": " + hand[i]);
        }
        int playerDiscardChoice = scnr.nextInt();
        while(playerDiscardChoice > cardsInHand || playerDiscardChoice < 0)
        {
            System.out.println("Invalid choice");
            playerDiscardChoice = scnr.nextInt();
        }
        for(int a = 0; a < cardsInHand; a++)
        {
            if (playerDiscardChoice == a)
            {
                treasureCards.treasureDiscard[treasureCards.currentDiscard] = hand[playerDiscardChoice];
                hand[playerDiscardChoice] = hand[a + 1];
            }
            else if (a == cardsInHand)
            {
                hand[a] = "no card";
            }
            else if (a > playerDiscardChoice)
            {
                hand[a] = hand[a + 1];
            }
        }
        cardsInHand--;
        treasureCards.currentDiscard++;
    }
    
    public int CaptureTreasure(String tileName)
    {
        currentTileName = tileName;
        if(numOfWaterTreasure == 4 && (currentTileName.equals("Coral Palace") || currentTileName.equals("Tidal Palace")))
        {
            piecesCollected[currentTreasureCount] = "Water Chalice";
            currentTreasureCount++;
            return 0;
        }
        else if (numOfFireTreasure == 4 && (currentTileName.equals("Cave of Embers") || currentTileName.equals("Cave of Shadows")))
        {
            piecesCollected[currentTreasureCount] = "Fire Crystal";
            currentTreasureCount++;
            return 0;
        }
        else if (numOfWindTreasure == 4 &&(currentTileName.equals("Whispering Garden") || currentTileName.equals("Howling Garden")))
        {
            piecesCollected[currentTreasureCount] = "Wind Statue";
            currentTreasureCount++;
            return 0;
        }
        else if (numOfEarthTreasure == 4 && (currentTileName.equals("Temple of the Sun") || currentTileName.equals("Temple of the Moon")))
        {
            piecesCollected[currentTreasureCount] = "Earth Stone";
            currentTreasureCount++;
            return 0;
        }
        else
        {
            System.out.println("Unable to capture a treasure");
            return -1;
        }
    }
}
    