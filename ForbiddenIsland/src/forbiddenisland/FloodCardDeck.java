package forbiddenisland;

/**
 *
 * @author Jeremy Miller
 */
public class FloodCardDeck {
    
    String[] cardNames = {"Fool\'s Landing", "Gold Gate", "Copper Gate",
                          "Silver Gate", "Bronze Gate", "Iron Gate",
                          "Coral Palace", "Tidal Palace", "Cave of Shadows",
                          "Cave of Embers", "Whispering Garden", "Howling Garden",
                          "Temple of the Sun", "Temple of the Moon", "Observatory",
                          "Breaker\'s Bridge", "Crimson Forest", "Twilight Hollow",
                          "Misty Marsh", "Phantom Rock", "Cliffs of Abandon",
                          "Dunes of Deception", "Watchtower", "Lost Lagoon"};
    String[] floodDeck = cardNames;
    int cardsLeftInDeck = 24;
    int maxCards = 24;
    String[] floodDiscard = new String[24];
    String drawnCard = "none";
    int discard = 0;
    
    public void FloodCardDeck(String[] floodDeck, String[] floodDiscard, String drawnCard,
                              int maxCards, int cardsLeftInDeck, int discard)
    {
        this.floodDeck = floodDeck;
        this.floodDiscard = floodDiscard;
        this.drawnCard = drawnCard;
        this.maxCards = maxCards;
        this.cardsLeftInDeck = cardsLeftInDeck;
        this.discard = discard;
    }
    public int GetCardsLeft()
    {
        return cardsLeftInDeck;
    }
    public String GetFloodCard(int cardNumber)
    {
        return floodDeck[cardNumber];
    }
    public void PrintFloodCards()
    {
        for(int i = 0; i < floodDeck.length; i++)
        {
            if (!floodDeck[i].equals("no card"))
                {
                    System.out.println(floodDeck[i]);
                }
        }
    }
    public void GetDiscard()
    {
        for(int i = 0; i < floodDiscard.length; i++)
        {
            if (floodDiscard != null && !floodDiscard.equals("no card"))
            {
                System.out.println(floodDiscard[i]);
            }
        }
    }
    public String DrawCard()
    {
        drawnCard = floodDeck[0];
        int next = 1;
        for (int i = 0; i < cardsLeftInDeck - 1; i++)
        {
            next = i;
            if (i == floodDeck.length)
            {
                floodDeck[i] = "no card";
            }
            else
            {
                floodDeck[i] = floodDeck[i + 1];
            }            
        }
        floodDeck[next + 1] = "no card";
        cardsLeftInDeck--;
        return drawnCard;
    }
    public String[] Shuffle()
    {
        for (int i = 0; i < cardsLeftInDeck; i++) {
            int index = (int) (Math.random() * cardsLeftInDeck);
            String temp = floodDeck[index];
            floodDeck[index] = floodDeck[i];
            floodDeck[i] = temp;
        }
        return floodDeck;
    }
    public void Refresh()
    {
        discard = 0;
        for (int i = 0; i < floodDeck.length; i++)
        {
            if(floodDeck[i] == null || floodDeck[i].equals("no card"))
            {
                floodDeck[i] = floodDiscard[discard];
                floodDiscard[discard] = "no card";
                discard++;
            }
        }
    }
    
    public void GetFloodDiscard()
    {
        System.out.println("TREASURE DISCARD");
        System.out.println("----------------");
        if(floodDiscard[0].equals("no card"))
        {
            System.out.println("No cards in discard");
            return;
        }
        else
        {
            for(int i = 0; i < floodDiscard.length; i++)
            {
                if(!floodDiscard[i].equals("no card"))
                {            
                    System.out.println(floodDiscard[i]);
                }
            }
        }
    }
}
