import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand = new ArrayList<Card>();

    public Player() {

    }

    public void addCard(Card newCard) {
        hand.add(newCard);
    }

    public int getNumCards() {
        return hand.size();
    }
}
