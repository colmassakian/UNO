import java.util.*;

public class Deck {

    enum Color {
        BLUE,
        GREEN,
        RED,
        YELLOW,
        WILD
    }

    Card[] cards = new Card[108];
    int currCard;

    public Deck() {
        currCard = 0;
        createDeck();
        shuffle();
    }

    private void createDeck() {
        int currIndex = 0;

        for (Color currColor : Color.values())
        {
            if(currColor == Color.WILD)
                continue;

            cards[currIndex] = new Card(0, currColor);
            currIndex ++;
        }

        for (Color currColor : Color.values())
        {
            if(currColor == Color.WILD)
                continue;

            for(int i = 1; i <= 12; i ++)
            {
                cards[currIndex] = new Card(i, currColor);
                currIndex ++;
                cards[currIndex] = new Card(i, currColor);
                currIndex ++;
            }
        }

        for(int i = 0; i < 4; i ++)
        {
            cards[currIndex] = new Card(13, Color.WILD);
            currIndex ++;
            cards[currIndex] = new Card(14, Color.WILD);
            currIndex ++;
        }

        System.out.println("MADE DECK " + currIndex);
    }

    private void shuffle() {
        int numShuffle = 80;
        int index1, index2;

        for(int i = 0; i < numShuffle; i ++)
        {
            index1 = (int)(Math.random() * 108);
            index2 = (int)(Math.random() * 108);

            Card temp = cards[index1];
            cards[index1] = cards[index2];
            cards[index2] = temp;
        }
    }

//    public Card deal() {
//
//    }
}
