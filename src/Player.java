import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand = new ArrayList<Card>();

    public Player() {

    }

    public void addCard(Card newCard) {
        hand.add(newCard);
    }

    public Card removeCard(int index) {
        return hand.remove(index);
    }

    public Card getCard(int index){
        return hand.get(index);
    }

    public int getNumCards() {
        return hand.size();
    }
}
