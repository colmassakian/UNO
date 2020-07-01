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
//        shuffle();
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

//    private void shuffle() {
//
//    }
//
//    public Card deal() {
//
//    }
}
