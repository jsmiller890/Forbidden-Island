package forbiddenisland;

import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author Jeremy Miller
 */

public class TreasureCardDeck {
    String[] cardNames = {"Helicopter", "Helicopter", "Helicopter", "Sandbag",
                                    "Sandbag", "Waters Rise", "Waters Rise",
                                    "Waters Rise", "Wind Treasure", "Wind Treasure",
                                    "Wind Treasure", "Wind Teasure", "Wind Treasure",
                                    "Water Treasure", "Water Treasure", "Water Treasure",
                                    "Water Treasure", "Water Treasure", "Earth Treasure",
                                    "Earth Treasure", "Earth Treasure", "Earth Treasure",
                                    "Earth Treasure", "Fire Treasure", "Fire Treasure",
                                    "Fire Treasure", "Fire Treasure", "Fire Treasure"};
    String[] treasureCards = cardNames;
    int cardsLeftInDeck = cardNames.length;
    String[] treasureDiscard = new String[28];
    String drawnCard = "none";
    int discard = 0;
    int currentDiscard = 0;
    
    public void TreasureCardDeck(String[] treasureCards, String[]treasureDiscard, 
                            String drawnCard,int cardsLeftInDeck, int discard, int currentDiscard)
    {
        this.treasureCards = treasureCards;
        this.treasureDiscard = treasureDiscard;
        this.drawnCard = drawnCard;
        this.cardsLeftInDeck = cardsLeftInDeck;
        this.discard = discard;
        this.currentDiscard = currentDiscard;
    }
    public int GetCardLeft()
    {
        return cardsLeftInDeck;
    }
    public void GetTreasureCards()
    {
        for (int i = 0; i < cardsLeftInDeck; i++)
        {
            if(!treasureCards[i].equals("no card"))
            {
                System.out.println(treasureCards[i]);
            }
        }
    }
    public void GetDiscard()
    {
        for (int i = 0; i < treasureDiscard.length; i++)
        {
            if(treasureDiscard[i] != null && !treasureDiscard[i].equals("no card"))
            {
                System.out.println(treasureDiscard[i]);
            }
        }
    }
    public String DrawCard()
    {
        drawnCard = treasureCards[0];
        int next = 1;
        for (int i = 0; i < cardsLeftInDeck - 1; i++)
        {
            next = i;
            if (i == treasureCards.length)
            {
                treasureCards[i] = "no card";
            }
            else
            {
                treasureCards[i] = treasureCards[i + 1];
            }            
        }
        treasureCards[next + 1] = "no card";
        cardsLeftInDeck--;
        System.out.println(drawnCard);
        return drawnCard;
    }
    public String[] ShuffleDeck()
    {
        for (int i = 0; i < cardsLeftInDeck; i++) {
            int index = (int) (Math.random() * cardsLeftInDeck);
            String temp = treasureCards[index];
            treasureCards[index] = treasureCards[i];
            treasureCards[i] = temp;
        }
        return cardNames;
    }
    public void Discard(String discardCard)
    {
        treasureDiscard[currentDiscard] = discardCard;
        treasureCards[0] = "no card";
        for(int a = 0; a < cardsLeftInDeck; a++)
        {
            treasureCards[a] = treasureCards[a + 1];
        }
        currentDiscard++;
    }
    public void Refresh()
    {
        for (int i = 0; i < treasureCards.length; i++)
        {
            if(treasureDiscard[discard].equals("no card"))
            {
                ShuffleDeck();
                ShuffleDeck();
                ShuffleDeck();
                break;
            }
            else if(treasureCards[i].equals("no card"))
            {
                treasureCards[i] = treasureDiscard[discard];
                treasureDiscard[discard] = "no card";
                discard++;
            }
        }
        discard = 0;
        currentDiscard = 0;
    }
    public void Restart()
    {
        
    }
}
